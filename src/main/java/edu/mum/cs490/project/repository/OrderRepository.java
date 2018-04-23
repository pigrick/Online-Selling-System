package edu.mum.cs490.project.repository;

import edu.mum.cs490.project.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByCustomer_id(Integer customerId);
}
