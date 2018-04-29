package edu.mum.cs490.project.controller.admin;


import edu.mum.cs490.project.domain.Product;
import edu.mum.cs490.project.service.CategoryService;
import edu.mum.cs490.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.file.Path;

/**
 * Created by ChanPiseth on 4/28/2018
 */

@Controller
@RequestMapping("/admin/product")
public class AdminProductController  {

    private Path path;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;


    public void setCreateProduct(Product product, Model model){
        model.addAttribute("categoryList", categoryService.getAllCategory());
        model.addAttribute("product", product);
        model.addAttribute("title", "Add Product");
    }

    @RequestMapping(value="/save", method=RequestMethod.GET)
    public String addProduct(@RequestParam(value="id", required=false) Integer productId, Model model) {
        // edit product
            // create product
            Product product = new Product();
            setCreateProduct(product, model);

        return "admin/saveProduct";
    }

}
