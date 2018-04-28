package edu.mum.cs490.project.controller.admin;

import edu.mum.cs490.project.domain.Category;
import edu.mum.cs490.project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by ChanPiseth on 4/28/2018
 */

@Controller
@RequestMapping(value= "/admin/category")
public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/m", method = RequestMethod.GET)
    public String categoryManagement(Model model){
        List<Category> categoryList = categoryService.getAllCategory();
        model.addAttribute("categoryList", categoryList);

        return "admin/categoryManagement";
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public String addCategory(@RequestParam(value = "id", required = false) Integer categoryId, Model model) {
        // edit category
        if(categoryId != null){
            Category category = categoryService.getCategoryById(categoryId);
            model.addAttribute("title", "Edit Category");
            model.addAttribute("category", category);
        }else{
            // create category
            Category category = new Category();
            model.addAttribute("title", "Add Category");
            model.addAttribute("category", category);
        }

        return "admin/saveCategory";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String addCategoryPost(@Valid @ModelAttribute("category") Category category, BindingResult result){

        if (result.hasErrors()) {
            return "admin/saveCategory";
        }
        categoryService.save(category);
        return "redirect:/admin/category/m";
    }

}
