package edu.mum.cs490.project.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.mum.cs490.project.controller.ProductController;
import edu.mum.cs490.project.service.CategoryService;
import edu.mum.cs490.project.service.ProductService;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;


@Entity
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category parentCategory;
    @NotBlank(message = "Can not be empty")
    private String name;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ENABLED;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Category getParentCategory() {
        return parentCategory;
    }
    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
