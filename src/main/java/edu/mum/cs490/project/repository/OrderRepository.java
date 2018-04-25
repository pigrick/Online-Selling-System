package edu.mum.cs490.project.repository;

import edu.mum.cs490.project.domain.Order;
import edu.mum.cs490.project.domain.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByCustomer_id(Integer customerId);

    @Query("select distinct o from Order o join o.orderDetails od WHERE od.product.vendor.id = :vendorId ")
    List<Order> findByVendor_id(@Param("vendorId") Integer VendorId);

    @Query("select distinct o from Order o join o.orderDetails od WHERE od.product.vendor.id = :vendorId AND o.orderDate between :begindate AND :enddate")
    List<Order> findByVendor_idBetweenDate(@Param("vendorId") Integer id, @Param("begindate") Date begin, @Param("enddate") Date end);
}
