package edu.mum.cs490.project.controller;

import edu.mum.cs490.project.domain.Customer;
import edu.mum.cs490.project.domain.Status;
import edu.mum.cs490.project.domain.User;
import edu.mum.cs490.project.domain.Vendor;
import edu.mum.cs490.project.model.Message;
import edu.mum.cs490.project.model.form.user.*;
import edu.mum.cs490.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Erdenebayar on 4/20/2018
 */
@Controller
@SuppressWarnings("unchecked")
public class SignController {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SignController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(value = "login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "signup", method = RequestMethod.GET)
    public String signUp(@AuthenticationPrincipal User user, ModelMap model) {
        if (user != null) {
            return "redirect://";
        }
        model.put("moduleForm", new CustomerSignUpForm());
        return "signUp";
    }

    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public String signUp(@Valid @ModelAttribute("moduleForm") CustomerSignUpForm userForm, BindingResult error, ModelMap model) {
        if (error.hasErrors()) {
            model.put("message", Message.errorOccurred);
            return "signUp";
        }

        if (userService.loadUserByUsername(userForm.getUsername()) != null) {
            error.rejectValue("username", "username.duplicate");
            return "signUp";
        }

        Customer customer = new Customer();
        setToUser(customer, userForm);

        customer.setFirstName(userForm.getFirstName());
        customer.setLastName(userForm.getLastName());

        userService.saveOrUpdate(customer);
//        ToDo Send registration Email
        model.put("message", Message.successfullySaved);
        return "signUp";
    }

    @RequestMapping(value = "vendor/signup", method = RequestMethod.GET)
    public String vendorSignUp(@AuthenticationPrincipal User user, ModelMap model) {
        if (user != null) {
            return "redirect://";
        }
        model.put("userForm", new UserForm());
        return "vendor/signup";
    }

    @RequestMapping(value = "vendor/signup", method = RequestMethod.POST)
    public String vendorSignUp(@Valid @ModelAttribute("moduleForm") VendorSignUpForm userForm, BindingResult error, ModelMap model) {
        if (error.hasErrors()) {
            model.put("message", Message.errorOccurred);
            return "vendor/signup";
        }

        if (userService.loadUserByUsername(userForm.getUsername()) != null) {
            error.rejectValue("username", "username.duplicate");
            return "vendor/signUp";
        }

        Vendor vendor = new Vendor();
        setToUser(vendor, userForm);

        vendor.setCompanyName(userForm.getCompanyName());

        userService.saveOrUpdate(vendor);
//        ToDo Send registration Email
        model.put("message", Message.successfullySaved);
        return "vendor/signUp";
    }

    private void setToUser(User user, UserSignUpForm form) {
        user.setUsername(form.getUsername());
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        user.setEmail(form.getEmail());
        user.setEmail(form.getEmail());
        user.setStatus(Status.ENABLED);
    }
}
