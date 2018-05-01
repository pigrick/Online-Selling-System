package edu.mum.cs490.project.controller;

import edu.mum.cs490.project.domain.*;
import edu.mum.cs490.project.model.ShoppingCart;
import edu.mum.cs490.project.model.form.CustomerOrderShippingForm;
import edu.mum.cs490.project.model.form.GuestOrderShippingForm;
import edu.mum.cs490.project.model.form.PaymentForm;
import edu.mum.cs490.project.service.OrderService;
import edu.mum.cs490.project.service.ProductService;
import edu.mum.cs490.project.service.impl.MockPaymentServiceImpl;
import edu.mum.cs490.project.utils.AESConverter;
import edu.mum.cs490.project.utils.SignedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("order")
public class OrderController {
    private final OrderService orderService;

    private final ProductService productService;

    private final MockPaymentServiceImpl mockPaymentService;

    private final AESConverter aesConverter;


    @Autowired
    public OrderController(OrderService orderService, MockPaymentServiceImpl mockPaymentService, ProductService productService, AESConverter aesConverter) {
        this.orderService = orderService;
        this.mockPaymentService = mockPaymentService;
        this.productService = productService;
        this.aesConverter  = aesConverter;
    }

    //Get all the orders depending on admin and vendor
    @GetMapping("all")
    public String getAllOrders(Model model, HttpSession session, HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            model.addAttribute("orders", this.orderService.findAll());
        } else if (request.isUserInRole("ROLE_CUSTOMER")) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Integer customerId = ((User) auth.getPrincipal()).getId();
            model.addAttribute("orders", this.orderService.findByCustomer_id(customerId));
        }
        return "order/orderlist";
    }

    @GetMapping("shoppingcart")
    public String getShoppingCart(Model model, HttpServletResponse response, HttpSession session) {
        ShoppingCart sc = new ShoppingCart();
        List<OrderDetail> od = new ArrayList<>();
        OrderDetail aa = new OrderDetail();
        Product p = productService.getOne(new Integer(1));
        aa.setProduct(p);
        aa.setPrice(p.getPrice());
        aa.setQuantity(3);
        Product b = productService.getOne(new Integer(2));
        OrderDetail bb = new OrderDetail();
        bb.setProduct(b);
        bb.setQuantity(5);
        bb.setPrice(b.getPrice());
        od.add(aa);
        od.add(bb);
        sc.setOrderDetails(od);
        session.setAttribute("shoppingcart", sc);
        return "order/shoppingcart";
    }

    @PostMapping("shoppingcart/update")
    public @ResponseBody
    String updateCart(HttpSession session, @RequestParam("productid") String productid, @RequestParam("updatedquantity") String quantity) {
        ShoppingCart sc = (ShoppingCart) session.getAttribute("shoppingcart");
        int pquantity = Integer.parseInt(quantity);
        for (OrderDetail od : sc.getOrderDetails()) {
            if (od.getProduct().getId().equals(new Integer(productid))) {
                if (pquantity == 0) {
                    sc.getOrderDetails().remove(od);
                } else {
                    od.setQuantity(pquantity);
                }

            }
        }

        session.setAttribute("shoppingcart", sc);
        return "success";
    }


    @GetMapping("checkout")
    public String checkoutFromShoppingCart(Model model, HttpSession session,
                                           @ModelAttribute("customerOrderShippingForm") CustomerOrderShippingForm customerOrderShippingForm) {
        ShoppingCart sc = (ShoppingCart) session.getAttribute("shoppingcart");
        if (sc == null || sc.getOrderDetails().isEmpty()) {
            return "order/emptycart";
        }
        return "order/checkoutcart";
    }

    @PostMapping("checkout")
    public String customerCheckout(@Valid CustomerOrderShippingForm customerOrderShippingForm, BindingResult bindingResult,
                                   @ModelAttribute("paymentForm") PaymentForm paymentForm, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "order/checkoutcart";
        } else {
            User user = SignedUser.getSignedUser();
            List<OrderDetail> orderdetails = ((ShoppingCart) session.getAttribute("shoppingcart")).getOrderDetails();
            Order order = new Order();
            order.receiveCustomerShippingForm(user, customerOrderShippingForm);
            order.setOrderDetails(orderdetails);

            session.setAttribute("checkoutorder", order);
            return "order/submitorder";
        }
    }

    @PostMapping("checkout/submit")
    public String customerOrderPayment(Model model, HttpSession session, @Valid PaymentForm paymentForm, BindingResult bindingResult,
                                       @RequestParam("month") String month, @RequestParam("year") String year) {
        System.out.println(bindingResult.toString());

        paymentForm.setCardNumber(paymentForm.getCardNumber().replaceAll("\\s",""));
        paymentForm.setCardExpirationDate(month + "/" + year);
        paymentForm.setLast4Digit(paymentForm.getCardNumber().substring(paymentForm.getCardNumber().length() - 4));

        Order order = (Order) session.getAttribute("checkoutorder");
        if (bindingResult.hasErrors()) {
            model.addAttribute("badcard", "Invalid Card details");
            return "order/submitorder";
        }
        User user = SignedUser.getSignedUser();
        order.receivePaymentFormAndEncrypt(paymentForm, this.aesConverter);

        Integer responseCode = mockPaymentService.purchase(System.currentTimeMillis() + "", paymentForm.getCardNumber(),
                paymentForm.getCardExpirationDate(), paymentForm.getCardHolderName(), paymentForm.getCvv(),
                paymentForm.getCardZipcode(), order.getTotalPriceWithTax(), "4322637205582291");


        if (responseCode != 1) {
            model.addAttribute("badcard", "Creditcard Declined!");
            return "/order/submitorder";
        }


        order.setOrderDate(new Date());
        order.setShippingDate(new Date());
        orderService.saveOrUpdate(order);
        System.out.println(order.getVendorPayment());
        //Send Email!!!!!!
        return "order/ordersuccess";
    }


    @GetMapping("guest/checkout")
    public String guestCheckoutFromShoppingCart(Model model,
                                                @ModelAttribute("guestOrderShippingForm") GuestOrderShippingForm guestOrderShippingForm) {
        return "order/guestcheckoutcart";
    }

    @PostMapping("guest/checkout")
    public String guestCheckout(@Valid GuestOrderShippingForm guestOrderShippingForm, BindingResult bindingResult,
                                @ModelAttribute("paymentForm") PaymentForm paymentForm,
                                HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "order/guestcheckoutcart";
        }
        Order order = new Order();
        order.receiveGuestShippingForm(guestOrderShippingForm);
        order.setOrderDetails(((ShoppingCart) session.getAttribute("shoppingcart")).getOrderDetails());

        session.setAttribute("checkoutorder", order);
        return "order/guestsubmitorder";

    }

    @PostMapping("guest/checkout/submit")
    public String guestOrderPayment(Model model, HttpSession session, @Valid PaymentForm paymentForm, BindingResult bindingResult,
                                    @RequestParam("month") String month, @RequestParam("year") String year) {
        paymentForm.setCardNumber(paymentForm.getCardNumber().replaceAll("\\s",""));
        paymentForm.setCardExpirationDate(month + "/" + year);
        paymentForm.setLast4Digit(paymentForm.getCardNumber().substring(paymentForm.getCardNumber().length() - 4));

        Order order = (Order) session.getAttribute("checkoutorder");
        if (bindingResult.hasErrors()) {
            model.addAttribute("badcard", "Invalid Card details");
            return "order/guestsubmitorder";
        }
        order.receivePaymentFormAndEncrypt(paymentForm, this.aesConverter);


        Integer responseCode = mockPaymentService.purchase(System.currentTimeMillis() + "", paymentForm.getCardNumber(),
                paymentForm.getCardExpirationDate(), paymentForm.getCardHolderName(), paymentForm.getCvv(),
                paymentForm.getCardZipcode(), order.getTotalPriceWithTax(), "4322637205582291");
        if (responseCode != 1) {
            model.addAttribute("badcard", "Creditcard Declined!");
            return "order/guestsubmitorder";
        }
        order.setOrderDate(new Date());
        order.setShippingDate(new Date());
        orderService.saveOrUpdate(order);

        //Send Email!!!!!!
        return "order/ordersuccess";
    }


    //Testing stuff
    @RequestMapping("yo")
    @ResponseBody
    public List<Order> getOrders() throws ParseException {
        Date begin = new SimpleDateFormat("yyyy-MM-dd").parse("2018-04-01");
        Date end = new SimpleDateFormat("yyyy-MM-dd").parse("2018-04-05");
        System.out.println(begin.toString());
        System.out.println(end.toString());
        return this.orderService.findByVendor_idBetweenDate(2, begin, end);

    }
}
