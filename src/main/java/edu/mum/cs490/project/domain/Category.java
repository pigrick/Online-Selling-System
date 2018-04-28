package edu.mum.cs490.project.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;


@Entity
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Category parentCategory;
    @NotBlank(message = "Can not be empty")
    private String name;

    @OneToMany
    @JsonIgnore
    private List<Product> products;


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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
