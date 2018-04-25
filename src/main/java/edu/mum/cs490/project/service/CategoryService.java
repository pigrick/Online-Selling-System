package edu.mum.cs490.project.service;

import edu.mum.cs490.project.domain.Category;

import java.util.List;


/**
 * Created by ChanPiseth on 4/20/2018
 */

public interface CategoryService {

    List<Category> getAllCategory();

    List<Category> getAllMainCategory();

    List<Category> getAllSubCategory(Integer parentId);

    void save(Category category);

    void delete(Integer categoryId);

    Category getCategoryById(Integer categoryId);
}
