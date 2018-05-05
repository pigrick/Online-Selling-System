package edu.mum.cs490.project.repository;

import edu.mum.cs490.project.domain.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Query("SELECT a FROM Product a WHERE " +
            "((:name IS NULL OR a.name like %:name%) OR (:name IS NULL OR a.vendor.name like %:name%)) AND " +
            "(:categoryId IS NULL OR a.category.id =:categoryId) AND " +
            "(:vendorId IS NULL OR a.vendor.id =:vendorId) AND " +
            "(:status IS NULL OR a.status =:status)")
    List<Product> find(@Param("name") String name, @Param("categoryId") Integer categoryId, @Param("vendorId") Integer vendorId, @Param("status") Status status, Sort sort);

    @Modifying
    @Query("update Product p set p.quantity = p.quantity - :pquantity where p.id = :productId")
    void deductProductAfterPurchase(@Param("pquantity") int pquantity, @Param("productId") Integer productId);
}
