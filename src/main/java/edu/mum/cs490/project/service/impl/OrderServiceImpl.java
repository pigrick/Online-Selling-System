package edu.mum.cs490.project.service.impl;

import edu.mum.cs490.project.domain.Order;
import edu.mum.cs490.project.repository.OrderRepository;
import edu.mum.cs490.project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {

    public final OrderRepository orderRespository;

    @Override
    public List<Order> findAll() {
        return this.orderRespository.findAll();
    }

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository){
        this.orderRespository = orderRepository;

    }

    //Order Manipulation
    @Override
    public List<Order> findByCustomer_id(Integer customerId) {
        return this.orderRespository.findByCustomer_id(customerId);
    }

    @Override
    public Order saveOrUpdate(Order order) {
        return this.orderRespository.save(order);
    }

}
