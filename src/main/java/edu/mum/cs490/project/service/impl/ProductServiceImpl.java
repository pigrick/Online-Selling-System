package edu.mum.cs490.project.service.impl;

import edu.mum.cs490.project.domain.Product;
import edu.mum.cs490.project.domain.Status;
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
    public Product getById(Integer id) {
        return productRepository.getById(id);
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
        Product product = getById(id);
        product.setStatus(Status.DELETED);
        productRepository.save(product);
    }

    @Override
    public void changeStatus(Integer id, Status status) {
        Product product = getById(id);
        product.setStatus(status);
        productRepository.save(product);
    }
}
