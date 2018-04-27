package edu.mum.cs490.project.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.mum.cs490.project.domain.*;
import edu.mum.cs490.project.model.ShoppingCart;
import edu.mum.cs490.project.model.form.CustomerOrderForm;
import edu.mum.cs490.project.model.form.GuestOrderForm;
import edu.mum.cs490.project.service.OrderService;
import edu.mum.cs490.project.utils.SignedUser;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("orders")
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
        return "";
    }

    @GetMapping("shoppingcart")
    public String getShoppingCart(Model model, HttpServletResponse response, @CookieValue(value="shoppingcart", defaultValue = "defaultshoppingcart")ShoppingCart shoppingCart){
        model.addAttribute("cart", shoppingCart);
        return "";
    }

    @GetMapping("checkout")
    public String checkoutFromShoppingCart(Model model,
                                           @ModelAttribute("orderForm")CustomerOrderForm customerOrderForm){
        return "";
    }

    @PostMapping("checkout/")
    public String checkout(@Valid CustomerOrderForm customerOrderForm,BindingResult bindingResult, HttpSession session){
        if(bindingResult.hasErrors()){
            return "";
        } else {
            User user = SignedUser.getSignedUser();
            Order order = new Order();
            order.setCustomer((Customer)user);

            session.setAttribute("checkoutorder", order);
            return "";
        }
    }

    @PostMapping("checkout/review")
    public String reviewOrder(Model model,HttpSession session){
        Order order = (Order)session.getAttribute("checkoutorder");
        return "";
    }


    @GetMapping("guest/checkout")
    public String guestCheckoutFromShoppingCart(Model model,
                                                @ModelAttribute("orderForm")GuestOrderForm ordeform){
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
