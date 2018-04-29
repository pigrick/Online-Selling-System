package edu.mum.cs490.project.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.mum.cs490.project.domain.*;
import edu.mum.cs490.project.model.ShoppingCart;
import edu.mum.cs490.project.model.form.CustomerOrderShippingForm;
import edu.mum.cs490.project.model.form.GuestOrderShippingForm;
import edu.mum.cs490.project.model.form.PaymentForm;
import edu.mum.cs490.project.service.OrderService;
import edu.mum.cs490.project.utils.SignedUser;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
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

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    //Get all the orders depending on admin and vendor
    @GetMapping("all")
    public String getAllOrders(Model model, HttpSession session, HttpServletRequest request){
        if(request.isUserInRole("ROLE_ADMIN")){
            model.addAttribute("orders", this.orderService.findAll());
        } else if (request.isUserInRole("ROLE_CUSTOMER")){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Integer customerId = ((User)auth.getPrincipal()).getId();
            model.addAttribute("orders", this.orderService.findByCustomer_id(customerId));
        }
        return "order/orderlist";
    }

    @GetMapping("shoppingcart")
    public String getShoppingCart(Model model, HttpServletResponse response, HttpSession session){
        ShoppingCart sc = new ShoppingCart();
        List<OrderDetail> od = new ArrayList<>();
        OrderDetail aa = new OrderDetail();
        Product p = new Product();
        p.setId(4);
        p.setName("Mac");
        aa.setProduct(p);
        aa.setPrice(3000);
        aa.setQuantity(3);
        Product b = new Product();
        b.setId(5);
        b.setName("PC");
        OrderDetail bb = new OrderDetail();
        bb.setProduct(b);
        bb.setQuantity(5);
        bb.setPrice(600);
        od.add(aa);
        od.add(bb);
        sc.setOrderDetails(od);
        session.setAttribute("shoppingcart", sc);
        return "order/shoppingcart";
    }

    @PostMapping("shoppingcart/update")
    public @ResponseBody String updateCart(HttpSession session, @RequestParam("productid") String productid, @RequestParam("updatedquantity") String quantity){
        ShoppingCart sc = (ShoppingCart) session.getAttribute("shoppingcart");
        int pquantity = Integer.parseInt(quantity);
        for(OrderDetail od : sc.getOrderDetails()){
            if(od.getProduct().getId().equals(new Integer(productid))){
                if(pquantity == 0){
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
                                           @ModelAttribute("customerOrderShippingForm")CustomerOrderShippingForm customerOrderShippingForm){
        return "order/checkoutcart";
    }

    @PostMapping("checkout")
    public String customerCheckout(@Valid CustomerOrderShippingForm customerOrderShippingForm, BindingResult bindingResult,
                           @ModelAttribute("paymentForm") PaymentForm paymentForm, HttpSession session){
        if(bindingResult.hasErrors()){
            return "order/checkoutcart";
        } else {
            User user = SignedUser.getSignedUser();
            Order order = new Order();
            order.setCustomer((Customer)user);

            session.setAttribute("checkoutorder", order);
            return "order/submitorder";
        }
    }

    @PostMapping("checkout/submit")
    public String customerOrderPayment(Model model, HttpSession session, @Valid PaymentForm paymentForm, BindingResult bindingResult){
        //Order order = (Order)session.getAttribute("checkoutorder");

        return "redirect:/order/all";
    }


    @GetMapping("guest/checkout")
    public String guestCheckoutFromShoppingCart(Model model,
                                                @ModelAttribute("guestOrderShippingForm")GuestOrderShippingForm guestOrderShippingForm){
        return "order/guestcheckoutcart";
    }
    @PostMapping("guest/checkout")
    public String guestCheckout(@Valid GuestOrderShippingForm GuestOrderShippingForm, BindingResult bindingResult,
                                   @ModelAttribute("paymentForm") PaymentForm paymentForm,
                                   HttpSession session){
        if(bindingResult.hasErrors()){
            return "order/guestcheckoutcart";
        } else {
            Order order = new Order();

            session.setAttribute("checkoutorder", order);
            return "order/submitorder";
        }
    }

    @PostMapping("guest/checkout/submit")
    public String guestOrderPayment(Model model, HttpSession session, @Valid PaymentForm paymentForm, BindingResult bindingResult){
        return "";
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
