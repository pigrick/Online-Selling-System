package edu.mum.cs490.project.service;

import edu.mum.cs490.project.domain.Category;

import java.util.List;


/**
 * Created by ChanPiseth on 4/20/2018
 */

public interface CategoryService {

    List<Category> getAllCategory();

    List<String> getAllMainCategory();

    List<String> getAllSubCategory();

    void save(Category category);

    void delete(Long categoryId);

    Category getCategoryById(Long categoryId);
}
