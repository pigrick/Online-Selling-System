package edu.mum.cs490.project.controller.admin;

import edu.mum.cs490.project.domain.Admin;
import edu.mum.cs490.project.domain.Customer;
import edu.mum.cs490.project.domain.Status;
import edu.mum.cs490.project.domain.Vendor;
import edu.mum.cs490.project.model.Message;
import edu.mum.cs490.project.model.form.user.AdminForm;
import edu.mum.cs490.project.model.form.user.AdminSignUpForm;
import edu.mum.cs490.project.model.form.user.CustomerForm;
import edu.mum.cs490.project.model.form.user.VendorForm;
import edu.mum.cs490.project.service.AdminService;
import edu.mum.cs490.project.service.CustomerService;
import edu.mum.cs490.project.service.UserService;
import edu.mum.cs490.project.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Erdenebayar on 4/29/2018
 */
@Controller
@RequestMapping(value = {"admin/user", "admin/u"})
public class AdminUserController {

    private final AdminService adminService;
    private final UserService userService;
    private final CustomerService customerService;
    private final VendorService vendorService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminUserController(AdminService adminService, UserService userService, CustomerService customerService, VendorService vendorService, PasswordEncoder passwordEncoder) {
        this.adminService = adminService;
        this.userService = userService;
        this.customerService = customerService;
        this.vendorService = vendorService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(value = {"a", "admin"})
    public String getAdmins(@RequestParam(required = false) String username,
                            @RequestParam(required = false) String firstName,
                            @RequestParam(required = false) String lastName,
                            @RequestParam(required = false, defaultValue = "ENABLED") Status status,
                            Model model) {
        model.addAttribute("statuses", Status.values());
        model.addAttribute("list", adminService.find(username, firstName, lastName, status));
        return "admin/user/admin/index";
    }

    @RequestMapping(value = {"d", "delete"})
    @ResponseBody
    public Message delete(@RequestParam Integer id,
                         Model model) {
        userService.delete(id);
//        model.addAttribute("message", new Message(Message.Type.SUCCESS, "successfully.deleted"));
        return new Message(Message.Type.SUCCESS, "successfully.deleted");
    }

    @RequestMapping(value = {"changeStatus"})
    @ResponseBody
    public Message changeStatus(@RequestParam Integer id, @RequestParam Status status,
                          Model model) {
        userService.changeStatus(id, status);
//        model.addAttribute("message", new Message(Message.Type.SUCCESS, "successfully.deleted"));
        return new Message(Message.Type.SUCCESS, "successfully.changed.status");
    }

    @GetMapping(value = {"a/e", "admin/edit"})
    public String editAdmin(@RequestParam Integer id, Model model) {
        Admin admin = adminService.getById(id);
        if (admin == null) {
            model.addAttribute("message", new Message(Message.Type.FAILED, "user.not.found"));
        }
        model.addAttribute("adminForm", new AdminForm(admin));
        return "admin/user/admin/edit";
    }

    @PostMapping(value = {"a/e", "admin/edit"})
    public String editAdmin(@Valid @ModelAttribute("adminForm") AdminForm adminForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("message", Message.errorOccurred);
            return "admin/user/admin/edit";
        }
        if (adminService.existByIdNotAndUsername(adminForm.getId(), adminForm.getUsername())) {
            model.addAttribute("message", new Message(Message.Type.FAILED, "username.duplicate"));
            result.rejectValue("username", "username.duplicate");
            return "admin/user/admin/edit";
        }
        Admin admin = adminService.getById(adminForm.getId());
        admin.setEmail(adminForm.getEmail());
        admin.setUsername(adminForm.getUsername());
        admin.setLastName(adminForm.getLastName());
        admin.setFirstName(adminForm.getFirstName());
        adminService.saveOrUpdate(admin);
        model.addAttribute("message", Message.successfullySaved);
        return "admin/user/admin/edit";
    }

    @GetMapping(value = {"a/c", "admin/create"})
    public String createAdmin(Model model) {
        model.addAttribute("adminForm", new AdminSignUpForm());
        return "admin/user/admin/create";
    }

    @PostMapping(value = {"a/c", "admin/create"})
    public String createAdmin(@Valid @ModelAttribute("adminForm") AdminSignUpForm adminForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("message", Message.errorOccurred);
            return "admin/user/admin/create";
        }
        if (adminService.getByUsername(adminForm.getUsername()) != null) {
            model.addAttribute("message", new Message(Message.Type.FAILED, "username.duplicate"));
            result.rejectValue("username", "username.duplicate");
            return "admin/user/admin/create";
        }
        Admin admin = new Admin();
        admin.setPassword(passwordEncoder.encode(adminForm.getPassword()));
        admin.setEmail(adminForm.getEmail());
        admin.setUsername(adminForm.getUsername());
        admin.setLastName(adminForm.getLastName());
        admin.setFirstName(adminForm.getFirstName());
        adminService.saveOrUpdate(admin);
        model.addAttribute("message", Message.successfullySaved);
        return "admin/user/admin/create";
    }

    @RequestMapping(value = {"m/v", "manage/vendor"})
    public String getAdmins(@RequestParam(required = false, defaultValue = "") String username,
                            @RequestParam(required = false, defaultValue = "") String companyName,
                            @RequestParam(required = false) Status status,
                            Model model) {
        model.addAttribute("statuses", Status.values());
        model.addAttribute("list", vendorService.find(username, companyName, status));
        return "admin/user/vendorManagement";
    }

    @GetMapping(value = {"e/v", "edit/vendor"})
    public String editVendor(@RequestParam Integer id, Model model) {
        Vendor vendor = vendorService.getById(id);
        if (vendor == null) {
            model.addAttribute("message", new Message(Message.Type.FAILED, "user.not.found"));
        }
        model.addAttribute("vendorForm", new VendorForm(vendor));
        return "admin/user/editVendor";
    }

    @PostMapping(value = {"e/v", "edit/vendor"})
    public String editVendor(@Valid @ModelAttribute("vendorForm") VendorForm vendorForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("message", Message.errorOccurred);
            return "admin/user/editVendor";
        }
        if (vendorService.existByIdNotAndUsername(vendorForm.getId(), vendorForm.getUsername())) {
            model.addAttribute("message", new Message(Message.Type.FAILED, "username.duplicate"));
            result.rejectValue("username", "username.duplicate");
            return "admin/user/editVendor";
        }
        Vendor vendor = vendorService.getById(vendorForm.getId());
        vendor.setEmail(vendorForm.getEmail());
        vendor.setUsername(vendorForm.getUsername());
        vendor.setCompanyName(vendorForm.getCompanyName());
        vendorService.saveOrUpdate(vendor);
        model.addAttribute("message", Message.successfullySaved);
        return "admin/user/editVendor";
    }

    @RequestMapping(value = {"m/c", "manage/customer"})
    public String getCustomers(@RequestParam(required = false, defaultValue = "") String username,
                               @RequestParam(required = false, defaultValue = "") String firstName,
                               @RequestParam(required = false, defaultValue = "") String lastName,
                               @RequestParam(required = false, defaultValue = "ENABLED") Status status,
                               Model model) {
        model.addAttribute("statuses", Status.values());
        model.addAttribute("list", customerService.find(username, firstName, lastName, status));
        return "admin/user/customerManagement";
    }

    @GetMapping(value = {"e/c", "edit/customer"})
    public String editCustomer(@RequestParam Integer id, Model model) {
        Customer customer = customerService.getById(id);
        if (customer == null) {
            model.addAttribute("message", new Message(Message.Type.FAILED, "user.not.found"));
        }
        model.addAttribute("customerForm", new CustomerForm(customer));
        return "admin/user/editCustomer";
    }

    @PostMapping(value = {"e/c", "edit/customer"})
    public String editCustomer(@Valid @ModelAttribute("customerForm") CustomerForm customerForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("message", Message.errorOccurred);
            return "admin/user/editCustomer";
        }
        if (customerService.existByIdNotAndUsername(customerForm.getId(), customerForm.getUsername())) {
            model.addAttribute("message", new Message(Message.Type.FAILED, "username.duplicate"));
            result.rejectValue("username", "username.duplicate");
            return "admin/user/editCustomer";
        }
        Customer customer = customerService.getById(customerForm.getId());
        customer.setEmail(customerForm.getEmail());
        customer.setUsername(customerForm.getUsername());
        customer.setLastName(customerForm.getLastName());
        customer.setFirstName(customerForm.getFirstName());
        customerService.saveOrUpdate(customer);
        model.addAttribute("message", Message.successfullySaved);
        return "admin/user/editCustomer";
    }

}
