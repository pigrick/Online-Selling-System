package edu.mum.cs490.project.controller;

import edu.mum.cs490.project.domain.Product;
import edu.mum.cs490.project.domain.Status;
import edu.mum.cs490.project.service.CategoryService;
import edu.mum.cs490.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    private final int PAGE_SIZE = 10;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String index(Model model) {
        return "product/index";
    }

    @GetMapping(value = {"list", "search"})
    public String getAllProduct(@RequestParam(required = false) String name,
                                @RequestParam(required = false) Integer categoryId,
                                @RequestParam(required = false) Integer vendorId,
                                //@RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(required = false) Boolean orderByPrice, Model model) {
        Sort orders = null;
        if (orderByPrice != null) {
            orders = orderByPrice ? Sort.by("price").ascending() : Sort.by("price").descending();
        }
        //Page<Product> productsList = this.productService.findPage(name, categoryId, vendorId, Status.ENABLED, PageRequest.of(page - 1, PAGE_SIZE, orders));
        //model.addAttribute("products", productsList.getContent());
        model.addAttribute("products", this.productService.find(name, categoryId, vendorId, Status.ENABLED, orders));
        model.addAttribute("categories", categoryService.find(null, null, Status.ENABLED));
        model.addAttribute("title", "Products:");
        return "product/products";
    }

    @GetMapping("/{productId}")
    public String getById(Model theModel, @PathVariable("productId") Integer productId) {
        theModel.addAttribute("product", productService.getOne(productId));
        return "product/product";
    }

}
