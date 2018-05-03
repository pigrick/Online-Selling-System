package edu.mum.cs490.project.model.form.user;

import edu.mum.cs490.project.domain.Category;
import edu.mum.cs490.project.domain.Status;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Created by ChanPiseth on 5/03/2018
 */

public class CategoryForm implements Serializable {

    @NotBlank
    private Category MainCategory;
    @NotBlank
    private String SubCategory;
    private edu.mum.cs490.project.domain.Status Status;


    public CategoryForm(Category category) {
        this.MainCategory  = category.getParentCategory();
        this.SubCategory = category.getName();
        this.Status = category.getStatus();
    }

    public Category getMainCategory() {
        return MainCategory;
    }

    public void setMainCategory(Category mainCategory) {
        MainCategory = mainCategory;
    }

    public String getSubCategory() {
        return SubCategory;
    }

    public void setSubCategory(String subCategory) {
        SubCategory = subCategory;
    }

    public edu.mum.cs490.project.domain.Status getStatus() {
        return Status;
    }

    public void setStatus(edu.mum.cs490.project.domain.Status status) {
        Status = status;
    }
}
