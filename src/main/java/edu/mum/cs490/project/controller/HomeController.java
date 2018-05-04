package edu.mum.cs490.project.controller;

import edu.mum.cs490.project.domain.Category;
import edu.mum.cs490.project.domain.Product;
import edu.mum.cs490.project.domain.Status;
import edu.mum.cs490.project.service.CategoryService;
import edu.mum.cs490.project.service.ProductService;
import edu.mum.cs490.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {

    public final UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @RequestMapping("/")
    public String getHome(Model model){


        List<Product> productList = productService.getAllProduct();
        List<Category> mainCategories = categoryService.find(null, null, Status.ENABLED);

        model.addAttribute("products", productList);
        model.addAttribute("mainCategories", mainCategories);

        return "index";
    }




}
