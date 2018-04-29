package edu.mum.cs490.project.service;

import edu.mum.cs490.project.domain.Product;
import edu.mum.cs490.project.domain.Status;
import edu.mum.cs490.project.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Pagmaa on 4/23/2018
 */

@Transactional(readOnly = true)
public interface ProductService {

    @Transactional
    Product getOne(Integer id);

    @Transactional
    List<Product> getAllProduct();

    @Transactional
    Product saveOrUpdate(Product product);

    @Transactional
    void delete(Integer id);

    @Transactional
    void changeStatus(Integer id, Status status);

    @Transactional
    List<Product> findByName(String productName);

    @Transactional
    List<Product> findByVendor(Integer vendor);

    @Transactional
    @PreAuthorize("#user.id == principal.id or hasRole('ROLE_ADMIN') or hasRole('ROLE_VENDOR')")
    void saveOrUpdateProduct(Product product);

    @Transactional
    void deleteProduct(Product product);

    @Transactional
    List<Product> findByCategory(Integer category);
}
