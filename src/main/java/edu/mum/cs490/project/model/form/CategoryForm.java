package edu.mum.cs490.project.model.form;

import edu.mum.cs490.project.domain.Category;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Created by Erdenebayar on 5/4/2018
 */
public class CategoryForm implements Serializable {

    private Integer id;
    @NotBlank(message = "Can not be empty")
    private String name;
    private Integer categoryId;

    public CategoryForm() {
    }

    public CategoryForm(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.categoryId = category.getParentCategory() != null ? category.getParentCategory().getId() : null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
