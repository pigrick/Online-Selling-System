package edu.mum.cs490.project;

import edu.mum.cs490.project.domain.Customer;
import edu.mum.cs490.project.domain.Product;
import edu.mum.cs490.project.domain.Vendor;
import edu.mum.cs490.project.model.Message;
import edu.mum.cs490.project.model.form.user.CategroyManagementForm;
import edu.mum.cs490.project.model.form.user.ProductManagmentForm;
import edu.mum.cs490.project.model.form.user.VendorSignUpForm;
import edu.mum.cs490.project.service.CategoryService;
import edu.mum.cs490.project.service.ProductService;
import edu.mum.cs490.project.service.VendorService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

/**
 * Created by ChanPiseth on 5/01/2018
 */

@RunWith(Runner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class TestProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private VendorService vendorService;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void checkforwardedUrl() throws Exception {

        mockMvc.perform(get("/admin/saveProduct")).andExpect(forwardedUrl("/WEB-INF/jsp/productManagement.jsp"));

    }
    @Test
    public void checkExistingProduct() throws Exception {
        ProductManagmentForm productForm = new ProductManagmentForm();
        VendorSignUpForm vendorForm = new VendorSignUpForm();
        CategroyManagementForm categroyManagementForm = new CategroyManagementForm();

        productForm.setDescription("Shoes");
        productForm.setName("Nike Air");
        productForm.setPrice(125);
        productForm.setQuantity(4);
        vendorForm.setCompanyName("Nike");
        categroyManagementForm.setId(4);

        mockMvc.perform(post("/admin/saveProduct").flashAttr("productForm", productForm))
                .andExpect(model().attribute("message", Message.successfullySaved));

        //Customer products = (Customer) productService.getOne(4);
        //Assert.assertNotNull(products);

    }

}
