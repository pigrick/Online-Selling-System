package edu.mum.cs490.project.service;

import edu.mum.cs490.project.domain.Order;
import edu.mum.cs490.project.repository.OrderRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface OrderService {

    @Async
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    List<Order> findAll();

    @Async
    List<Order> findByCustomer_id(Integer customerId);

    @Transactional
    Order saveOrUpdate(Order order);

}
