package edu.mum.cs490.project.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.mum.cs490.project.domain.Customer;
import edu.mum.cs490.project.domain.Order;
import edu.mum.cs490.project.domain.User;
import edu.mum.cs490.project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping("all")
    public String getAllOrders(Model model, HttpSession session, HttpServletRequest request){
        if(request.isUserInRole("ROLE_ADMIN")){
            model.addAttribute("orders", this.orderService.findAll());
        } else if (request.isUserInRole("ROLE_CUSTOMER")){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Integer customerId = ((User)auth.getPrincipal()).getId();
            model.addAttribute("orders", this.orderService.findByCustomer_id(customerId));
        }


        model.addAttribute("orders", this.orderService.findAll());
        return "";
    }


}
