package edu.mum.cs490.project.controller.admin;

import edu.mum.cs490.project.domain.Category;
import edu.mum.cs490.project.domain.Status;
import edu.mum.cs490.project.model.form.CategoryForm;
import edu.mum.cs490.project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by ChanPiseth on 4/28/2018
 */

@Controller
@RequestMapping(value = "/admin/category")
public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String index(Model model) {
        List<Category> list = categoryService.find(null, null, Status.ENABLED);
        model.addAttribute("statuses", Status.values());
        model.addAttribute("list", list);
        return "admin/category/index";
    }

    @GetMapping("list")
    public String list(@RequestParam(required = false) String name,
                       @RequestParam(required = false) Integer parentId,
                       @RequestParam(required = false, defaultValue = "ENABLED") Status status,
                       Model model) {
        List<Category> categoryList = categoryService.find(name, parentId, status);
        model.addAttribute("categoryList", categoryList);
        return "admin/category/list";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String addCategory(@RequestParam(value = "id", required = false) Integer categoryId, Model model) {
        // edit category
        CategoryForm categoryForm;
        if (categoryId != null) {
            model.addAttribute("title", "Edit Category");
            categoryForm = new CategoryForm(categoryService.getCategoryById(categoryId));
        } else {
            // create category
            model.addAttribute("title", "Add Category");
            categoryForm = new CategoryForm();
        }
        model.addAttribute("categoryForm", categoryForm);
        return "admin/category/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String addCategoryPost(@Valid @ModelAttribute("categoryForm") CategoryForm categoryForm, BindingResult result) {

        if (result.hasErrors()) {
            return "admin/category/create";
        }
        Category category;
        if (categoryForm.getId() != null)
            category = categoryService.getCategoryById(categoryForm.getCategoryId());
        else
            category = new Category();
        category.setName(categoryForm.getName());
//        category.setParentCategory();
        categoryService.save(category);
        return " redirect:/admin/category/m";
    }

    @RequestMapping("/delete")
    public String deleteProductById(@RequestParam(value = "id", required = true) Integer categoryId) {

        categoryService.delete(categoryId);

        return "redirect:/admin/category/m";
    }

    @ModelAttribute("categories")
    public List<Category> modelCategories() {
        return categoryService.find(null, null, Status.ENABLED);
    }


}
