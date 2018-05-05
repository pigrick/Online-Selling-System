package edu.mum.cs490.project.controller;

import edu.mum.cs490.project.domain.Status;
import edu.mum.cs490.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = {"all", "search"})
    public String getAllProduct(@RequestParam(required = false) String name,
                                @RequestParam(required = false) Integer categoryId,
                                @RequestParam(required = false) String vendorName,
                                @RequestParam(required = false) Integer vendorId, Model theModel) {
        theModel.addAttribute("products", this.productService.find(name, categoryId, vendorId, Status.ENABLED, null));
        theModel.addAttribute("title", "Products:");
        return "product/products";
    }

    @GetMapping("/{productId}")
    public String getById(Model theModel, @PathVariable("productId") Integer productId) {
        theModel.addAttribute("product", productService.getOne(productId));
        return "product/product";
    }

}
