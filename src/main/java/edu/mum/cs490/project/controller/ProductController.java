package edu.mum.cs490.project.controller;

import edu.mum.cs490.project.domain.Product;
import edu.mum.cs490.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("all")
    public String getAllProduct(Model theModel){
        theModel.addAttribute("products" , this.productService.getAllProduct());
        theModel.addAttribute("title", "Products:");
        return "products";
         //return "list-all-products" ;
    }

    @GetMapping("/{productId}")
    public String getById(Model theModel, @PathVariable("productId") Integer productId){

        Product theProduct=this.productService.getOne(productId);

        theModel.addAttribute("product", theProduct);

        return "product";
        //return "get-by-product-id";
    }

    // /product/name
    @GetMapping("name/{productName}")
    public String findByProductName(Model theModel, @PathVariable("productName") String productName){
        List<Product> products = this.productService.findByName(productName);

        theModel.addAttribute("products", products);

        return " ";
        //return "find-by-product-name";
    }

    /*@GetMapping("/{vendor}")
    public String findByVendor(Model theModel, @PathVariable("vendor") Integer vendor){
        List<Product> products = this.productService.findByVendor(vendor);

        theModel.addAttribute("products", products);

        return " ";
        //return "find-by-product-vendor";
    }
*/


    @GetMapping("category/{category}")
    public String findByCategory(Model theModel, @PathVariable("category") Integer category){
        List<Product> products = this.productService.findByCategory(category);

        theModel.addAttribute("products", products);

        return " ";
        //return "find-by-product-vendor";
    }
}
