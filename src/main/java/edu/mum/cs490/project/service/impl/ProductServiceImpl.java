package edu.mum.cs490.project.service.impl;

import edu.mum.cs490.project.domain.Product;
import edu.mum.cs490.project.domain.Status;
import edu.mum.cs490.project.repository.ProductRepository;
import edu.mum.cs490.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public List<Product> find(String name, Integer categoryId, Integer vendorId, Status status, Sort sort) {
        return productRepository.find(name, categoryId, vendorId, status, sort);
    }

    @Override
    public Page<Product> findPage(String name, Integer categoryId, Integer vendorId, Status status, Pageable pageable) {
        return productRepository.findPage(name, categoryId, vendorId, status, pageable);
    }
}
