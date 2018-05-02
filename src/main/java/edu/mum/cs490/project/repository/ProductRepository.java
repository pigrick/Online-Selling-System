package edu.mum.cs490.project.repository;

import edu.mum.cs490.project.domain.Category;
import edu.mum.cs490.project.domain.Status;
import edu.mum.cs490.project.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import edu.mum.cs490.project.domain.Product;

import java.util.List;

/**
 * Created by Pagmaa on 4/23/2018
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByNameIsContaining(String productName);

    List<Product> findByVendor_Id(Integer id);

    List<Product> findByCategory_Id(Integer category);

    List<Product> findByStatusIsTrue();

    List<Product> findByVendorAndStatus(Vendor vendor, Status status);
}
