package edu.mum.cs490.project.service;

import edu.mum.cs490.project.domain.CardDetail;
import edu.mum.cs490.project.domain.Order;
import edu.mum.cs490.project.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional(readOnly = true)
public interface OrderService {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    List<Order> findAll();

    List<Order> findallEnabledByCustomer_id(Integer customerId);

    List<Order> findByCustomer_id(Integer customerId);

    List<Order> findByVendor_id(Integer vendorId);

    Order findById(Integer orderId);

    Page<Order> findByCustomer_id(Integer customerId, int page);

    List<Order> findByVendor_idBetweenDate(Integer vendorId, Date begin, Date end);

    List<CardDetail> findCardByUser_id(Integer userId);

    CardDetail findCardById(Integer cardId);

    @Transactional
    void disableCard(Integer cardId);

    @Transactional
    Order saveOrUpdate(Order order);

    @Transactional
    Integer purchase(Order order);



}
