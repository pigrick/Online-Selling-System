package edu.mum.cs490.project.controller.admin;

import edu.mum.cs490.project.domain.Category;
import edu.mum.cs490.project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/admin/category")
public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/m")
    public String categoryManagement(Model model){
        List<Category> categoryList = categoryService.getAllCategory();
        model.addAttribute("categoryList", categoryList);

        return "admin/categoryManagement";
    }

}
