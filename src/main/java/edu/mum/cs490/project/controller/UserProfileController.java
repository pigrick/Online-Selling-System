package edu.mum.cs490.project.controller;

import edu.mum.cs490.project.domain.Customer;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by Erdenebayar on 4/24/2018
 */
@SuppressWarnings("unchecked")
@Controller
@RequestMapping(value ="profile")
public class UserProfileController {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserProfileController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping
    public String getProfile() {
        return "profile/index";
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String profileEdit(@AuthenticationPrincipal Customer user, ModelMap model) {
        model.put("editForm", new CustomerForm(user));
       return "profile/editCustomer";

    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String profileEdit(@AuthenticationPrincipal Customer user, @Valid @ModelAttribute("editForm") CustomerForm editForm, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.put("message", Message.errorOccurred);
            return "profile/editCustomer";
        }

        if (userService.existByIdNotAndUsername(user.getId(), editForm.getUsername())){
            model.put("message", new Message(Message.Type.ERROR, "username.duplicate"));
            result.rejectValue("username", "username.duplicate");
            return "profile/editVendor";
        }
        Customer customer = (Customer) userService.getById(3);

        user.setEmail(editForm.getEmail());
        user.setUsername(editForm.getUsername());
        user.setFirstName(editForm.getFirstName());
        user.setLastName(editForm.getLastName());
        userService.saveOrUpdate(user);
        model.put("message", Message.successfullySaved);
        return "profile/editCustomer";
    }

    @RequestMapping(value = "vendor/edit", method = RequestMethod.GET)
    public String VendorProfileEdit(@AuthenticationPrincipal Vendor user, ModelMap model) {
        model.put("editForm", new VendorForm(user));
        return "profile/editVendor";
    }

    @RequestMapping(value = "vendor/edit", method = RequestMethod.POST)
    public String VendorProfileEdit(@AuthenticationPrincipal Vendor user, @Valid @ModelAttribute("editForm") VendorForm editForm, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.put("message", Message.errorOccurred);
            return "profile/editVendor";
        }

        if (userService.existByIdNotAndUsername(editForm.getId(), editForm.getUsername())){
            model.put("message", new Message(Message.Type.ERROR, "username.duplicate"));
            result.rejectValue("username", "username.duplicate");
            return "profile/editVendor";
        }
        user.setEmail(editForm.getEmail());
        user.setUsername(editForm.getUsername());
        user.setCompanyName(editForm.getCompanyName());
        userService.saveOrUpdate(user);
        model.put("message", Message.successfullySaved);
        model.put("editForm", new VendorForm(user));
        return "profile/editVendor";
    }

    @RequestMapping(value = "edit/password", method = RequestMethod.GET)
    public String profileEditPassword(ModelMap model) {
        model.put("passwordForm", new PasswordForm());
        return "profile/editPassword";
    }

    @RequestMapping(value = "edit/password", method = RequestMethod.POST)
    public String profileEditPassword(@AuthenticationPrincipal User user, @Valid @ModelAttribute("passwordForm") PasswordForm form, BindingResult error, ModelMap model) {
        if (error.hasErrors()) {
            model.put("message", Message.errorOccurred);
            return "profile/editPassword";
        }
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        userService.saveOrUpdate(user);

        model.put("message", Message.successfullySaved);

        return "profile/editPassword";
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String delete(@AuthenticationPrincipal User user, RedirectAttributes model) {

        userService.delete(user.getId());

        model.addFlashAttribute("message", new Message(Message.Type.SUCCESS, "successfully.deleted"));

        return "redirect://";
    }
}
