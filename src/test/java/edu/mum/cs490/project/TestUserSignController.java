package edu.mum.cs490.project;

import edu.mum.cs490.project.domain.Customer;
import edu.mum.cs490.project.domain.User;
import edu.mum.cs490.project.domain.Vendor;
import edu.mum.cs490.project.model.Message;
import edu.mum.cs490.project.model.form.CustomerSignUpSignUpForm;
import edu.mum.cs490.project.model.form.VendorSignUpForm;
import edu.mum.cs490.project.service.CustomerService;
import edu.mum.cs490.project.service.VendorService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Created by Erdenebayar on 4/25/2018
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestUserSignController {

    @MockBean
    private CustomerService customerService;

    @MockBean
    private VendorService vendorService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void checkforwardedUrl() throws Exception {

        mockMvc.perform(get("/login")).andExpect(forwardedUrl("/WEB-INF/jsp/login.jsp")).andExpect(model().attribute("message", "helloworld"));
        mockMvc.perform(get("/vendor/signup")).andExpect(forwardedUrl("/WEB-INF/jsp/vendor/signup.jsp")).andExpect(model().attribute("message", "helloworld"));
        mockMvc.perform(get("/signup")).andExpect(forwardedUrl("/WEB-INF/jsp/signup.jsp")).andExpect(model().attribute("message", "helloworld"));
//        Admin test = (Admin) userService.saveOrUpdate(admin);

    }

    @Test
//    @WithMockUser(username="admin",roles={"CUSTOMER","VENDOR", "ADMIN"})
    public void checkExistingUser() throws Exception {

        CustomerSignUpSignUpForm signUpForm = new CustomerSignUpSignUpForm();
        VendorSignUpForm vendorSignUpForm = new VendorSignUpForm();

        signUpForm.setFirstName("Erdenebayar");
        signUpForm.setLastName("Batsukh");
        signUpForm.setPassword("hello");
        signUpForm.setUsername("erdenebayar");
        signUpForm.setEmail("ebatsukh@mum.edu");

        vendorSignUpForm.setCompanyName("Erdenebayar");
        vendorSignUpForm.setPassword("hello");
        vendorSignUpForm.setUsername("erdenebayar");
        vendorSignUpForm.setEmail("ebatsukh@mum.edu");

        // Check User Signup
        mockMvc.perform(post("/signup").flashAttr("moduleForm", signUpForm))
                .andExpect(model().attributeHasFieldErrors("moduleForm", "username"));

        // Check Vendor Signup
        mockMvc.perform(post("/vendor/signup").flashAttr("moduleForm", vendorSignUpForm))
                .andExpect(model().attributeHasFieldErrors("moduleForm", "username"));
    }

    @Test
    public void isSavingToDatabase() throws Exception {

        CustomerSignUpSignUpForm signUpForm = new CustomerSignUpSignUpForm();
        VendorSignUpForm vendorSignUpForm = new VendorSignUpForm();

        signUpForm.setFirstName("Erdenebayar");
        signUpForm.setLastName("Batsukh");
        signUpForm.setPassword("hello");
        signUpForm.setUsername("uniqueUser1");
        signUpForm.setEmail("ebatsukh@mum.edu");

        vendorSignUpForm.setCompanyName("Erdenebayar");
        vendorSignUpForm.setPassword("hello");
        vendorSignUpForm.setUsername("uniqueUser2");
        vendorSignUpForm.setEmail("ebatsukh@mum.edu");

        mockMvc.perform(post("/signup").flashAttr("moduleForm", signUpForm))
                .andExpect(model().attribute("message", new Message(Message.Type.SUCCESS, "Successfully Registered")));

        mockMvc.perform(post("/vendor/signup").flashAttr("moduleForm", vendorSignUpForm))
                .andExpect(model().attribute("message", new Message(Message.Type.SUCCESS, "Successfully Registered")));


        check();
    }

    @Test
    public void check(){
        Customer customers = (Customer) customerService.loadUserByUsername("yeerick");
        Vendor vendors = (Vendor) vendorService.loadUserByUsername("vendor");
        List<Vendor> list = vendorService.getAll();

        Assert.assertNull(customers);
        Assert.assertNull(vendors);
    }
}
