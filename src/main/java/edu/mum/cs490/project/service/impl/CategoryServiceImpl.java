package edu.mum.cs490.project.service.impl;

import edu.mum.cs490.project.domain.Category;
import edu.mum.cs490.project.domain.Status;
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

    private final CategoryRepository categoryRepostitory;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepostitory) {
        this.categoryRepostitory = categoryRepostitory;
    }


    @Override
    public List<Category> getAllCategory() {
        return categoryRepostitory.findAll();
    }

    @Override
    public Category getOne(Integer id) {
        return null;
    }

    @Override
    public List<Category> getAllMainCategory() {
        return categoryRepostitory.findAllByParentCategoryIsNull();
    }

    @Override
    public List<Category> getAllSubCategory(Integer parentId) {
        return categoryRepostitory.findAllByParentCategoryId(parentId);
    }

    @Override
    public void save(Category category) {
        categoryRepostitory.save(category);
    }

    @Override
    public void delete(Integer categoryId) {
        Category category = getOne(categoryId);
        category.setStatus(Status.DELETED);
        categoryRepostitory.save(category);
    }

    @Override
    public Category getCategoryById(Integer categoryId) {
        return categoryRepostitory.getOne(categoryId);
    }




}
