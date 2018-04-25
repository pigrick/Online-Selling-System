package edu.mum.cs490.project.service.impl;

import edu.mum.cs490.project.domain.Category;
import edu.mum.cs490.project.repository.CategoryRepository;
import edu.mum.cs490.project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ChanPiseth on 4/24/2018
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepostitory;
    @Override
    public List<Category> getAllCategory() {
        return (List<Category>) categoryRepostitory.findAll();
    }

    @Override
    public List<String> getAllMainCategory() {
        return categoryRepostitory.findAllMainCategoryName();
    }

    @Override
    public List<String> getAllSubCategory() {
        return categoryRepostitory.findAllSubCategoryName();
    }

    @Override
    public void save(Category category) {
        categoryRepostitory.save(category);
    }

    @Override
    public void delete(Long categoryId) {

    }

    @Override
    public Category getCategoryById(Long categoryId) {
        return null;
    }
}
