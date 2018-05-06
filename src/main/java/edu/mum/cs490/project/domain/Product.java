package edu.mum.cs490.project.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToOne
    private Category category;
    @ManyToOne
    @JsonIgnore
    private Vendor vendor;
    private int quantity;
    private double price;
    private String description;
    private String image;
    @Enumerated(EnumType.STRING)
    private Status status = Status.ENABLED;

//    @Transient
//    private Set<Integer> parentIds = new HashSet<>();


    public Product() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

   /* public Set<Integer> getParentIds() {
        this.parentIds.add(category.getId());
        if (category.getParentCategory() != null) {
            this.parentIds.add(category.getParentCategory().getId());
            if (category.getParentCategory().getParentCategory() != null) {
                this.parentIds.add(category.getParentCategory().getParentCategory().getId());
            }
        }
        return parentIds;
    }

    public void setParentIds(Set<Integer> parentIds) {
        this.parentIds = parentIds;
    }*/
}
