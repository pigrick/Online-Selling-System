package edu.mum.cs490.project;

import edu.mum.cs490.project.domain.Customer;
import edu.mum.cs490.project.model.Message;
import edu.mum.cs490.project.model.form.user.CustomerForm;
import edu.mum.cs490.project.service.CustomerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Erdenebayar on 4/27/2018
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class TestProfileController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithUserDetails(value = "yeerick")
    public void changingUsernameWithExistingUsername() throws Exception{

        CustomerForm customerForm = new CustomerForm();
        customerForm.setFirstName("firstname");
        customerForm.setLastName("lastname");
        customerForm.setUsername("erdenebayar");

        mockMvc.perform(post("/profile/edit").flashAttr("editForm", customerForm))
                .andExpect(model().attributeHasFieldErrors("editForm", "username"));

    }

    @Test
    @WithUserDetails(value = "yeerick")
    public void check() throws Exception{

        CustomerForm customerForm = new CustomerForm();
        customerForm.setFirstName("firstname");
        customerForm.setLastName("lastname");
        customerForm.setUsername("yeerick");

        mockMvc.perform(post("/profile/edit").flashAttr("editForm", customerForm))
                .andExpect(model().attribute("message", Message.successfullySaved));

        Customer customer = (Customer) customerService.loadUserByUsername("yeerick");

        Assert.assertEquals(customer.getFirstName(), "firstname");

    }
}
