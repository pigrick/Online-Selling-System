package edu.mum.cs490.project.repository;

import edu.mum.cs490.project.domain.Category;
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

   Product getById(Integer id);

//   List<Product> getAllProduct();

   @Query("select p from Product where p.productName like%:productName% ")
   List<Product> findByProductName(@Param("productName") String productName);

    @Query("select p from Product where p.vendor like %:vendor% ")
    List<Product> findByVendor(@Param("vendor") String vendor);

    @Query("select p from Product p where p.category.name like (:category)%")
    List<Product> findByCategory(String category);
}
