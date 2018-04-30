package edu.mum.cs490.project.controller.admin;


import edu.mum.cs490.project.domain.Category;
import edu.mum.cs490.project.domain.Product;
import edu.mum.cs490.project.domain.Status;
import edu.mum.cs490.project.domain.Vendor;
import edu.mum.cs490.project.model.Message;
import edu.mum.cs490.project.model.form.ProductForm;
import edu.mum.cs490.project.service.CategoryService;
import edu.mum.cs490.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.file.Path;
import java.util.List;

@Controller
@RequestMapping("/admin/product")
public class AdminProductController {

    private Path path;

    private final ProductService productService;

    private final CategoryService categoryService;

    @Autowired
    public AdminProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/m", method = RequestMethod.GET)
    public String productManagement(Model model) {
        List<Product> productsList = productService.getAllProduct();
        model.addAttribute("productList", productsList);

        return "admin/productManagement";
    }

    @GetMapping("/save")
    public String saveOrUpdateProduct(@RequestParam(required = false) Integer id, Model model) {


        if (id != null && id != 0) {
            model.addAttribute("productForm", new ProductForm(productService.getOne(id)));
        } else {
            model.addAttribute("productForm", new ProductForm());
        }
        model.addAttribute("categories", categoryService.getAllMainCategory());

        //return "";
        return "admin/saveProduct";
    }

    @PostMapping("/save")
    public String saveOrUpdateProduct(@Valid @ModelAttribute("productForm") ProductForm form, BindingResult result,
                                      @AuthenticationPrincipal Vendor vendor, Model model) {

        model.addAttribute("categories", categoryService.getAllMainCategory());

        if (result.hasErrors()) {
            model.addAttribute("message", new Message(Message.Type.FAILED, "Check your forms!!!"));
            return "admin/saveProduct";
        }


        Product product = new Product();


        return "admin/saveProduct";
    }

}
