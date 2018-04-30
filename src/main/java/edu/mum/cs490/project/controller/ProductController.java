package edu.mum.cs490.project.controller;

import edu.mum.cs490.project.domain.Product;
import edu.mum.cs490.project.domain.Status;
import edu.mum.cs490.project.domain.Vendor;
import edu.mum.cs490.project.model.Message;
import edu.mum.cs490.project.model.form.ProductForm;
import edu.mum.cs490.project.service.CategoryService;
import edu.mum.cs490.project.service.ProductService;
import edu.mum.cs490.project.utils.SignedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("all")
    public String getAllProduct(Model theModel){
        theModel.addAttribute("products" , this.productService.getAllProduct());
        return "products";
         //return "list-all-products" ;
    }

    @GetMapping("/{productName}")
    public String findByProductName(Model theModel, @PathVariable("productName") String productName){
        List<Product> products = this.productService.findByName(productName);

        theModel.addAttribute("products", products);

        return " ";
        //return "find-by-product-name";
    }

    @GetMapping("/{vendor}")
    public String findByVendor(Model theModel, @PathVariable("vendor") Integer vendor){
        List<Product> products = this.productService.findByVendor(vendor);

        theModel.addAttribute("products", products);

        return " ";
        //return "find-by-product-vendor";
    }

    @GetMapping("/{category}")
    public String findByCategory(Model theModel, @PathVariable("category") Integer category){
        List<Product> products = this.productService.findByCategory(category);

        theModel.addAttribute("products", products);

        return " ";
        //return "find-by-product-vendor";
    }
}
