package edu.mum.cs490.project.repository;

import edu.mum.cs490.project.domain.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer>{
}
