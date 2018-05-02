package edu.mum.cs490.project.service.impl;

import edu.mum.cs490.project.domain.Product;
import edu.mum.cs490.project.domain.Status;
import edu.mum.cs490.project.domain.Vendor;
import edu.mum.cs490.project.repository.ProductRepository;
import edu.mum.cs490.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Pagmaa on 4/23/2018
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getOne(Integer id) {
        return productRepository.getOne(id);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Integer id) {
        Product product = getOne(id);
        product.setStatus(Status.DELETED);
        productRepository.save(product);
    }

    @Override
    public void changeStatus(Integer id, Status status) {
        Product product = getOne(id);
        product.setStatus(status);
        productRepository.save(product);
    }

    @Override
    public List<Product> findByName(String productName) {
        return this.productRepository.findByNameIsContaining(productName);
    }

    @Override
    public List<Product> findByVendor(Integer vendorId) {
        return this.productRepository.findByVendor_Id(vendorId);
    }
    @Override
    public void saveOrUpdateProduct(Product product) {
        this.productRepository.save(product);
    }

    @Override
    public void deleteProduct(Product product) {
        this.productRepository.delete(product);
    }

    @Override
    public List<Product> findByCategory(Integer categoryId) {
        return this.productRepository.findByCategory_Id(categoryId);
    }
    @Override
    public List<Product> findByStatusIsTrue() {
        return this.productRepository.findByStatusIsTrue();
    }

    @Override
    public List<Product>  findByVendorAndStatus(Vendor vendor, Status status) {
        return this.productRepository.findByVendorAndStatus(vendor,status);
    }

}
