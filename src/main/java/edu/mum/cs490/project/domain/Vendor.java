package edu.mum.cs490.project.domain;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Vendor extends User{
    private String companyName;
    @OneToMany(mappedBy = "vendor")
    private List<Product> products;

    public Vendor(){
        super();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
