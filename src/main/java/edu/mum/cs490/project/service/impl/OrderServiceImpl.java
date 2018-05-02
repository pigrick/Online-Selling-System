package edu.mum.cs490.project.service.impl;

import edu.mum.cs490.project.domain.Order;
import edu.mum.cs490.project.domain.Status;
import edu.mum.cs490.project.repository.AddressRepository;
import edu.mum.cs490.project.repository.OrderRepository;
import edu.mum.cs490.project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {

    public final OrderRepository orderRespository;

    public final AddressRepository addressRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, AddressRepository addressRepository){
        this.orderRespository = orderRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Order> findAll() {
        return this.orderRespository.findAll();
    }

    @Override
    public List<Order> findallEnabledByCustomer_id(Integer customerId) {
        return this.orderRespository.findByStatusAndCustomer_id(Status.ENABLED, customerId);
    }



    //Order Manipulation
    @Override
    public List<Order> findByCustomer_id(Integer customerId) {
        return this.orderRespository.findByCustomer_id(customerId);
    }

    @Override
    public List<Order> findByVendor_id(Integer vendorId) {
        return this.orderRespository.findByVendor_id(vendorId);
    }

    @Override
    public List<Order> findByVendor_idBetweenDate(Integer vendorId, Date begin, Date end) {
        return this.orderRespository.findByVendor_idBetweenDate(vendorId, begin, end);
    }

    @Override
    public Order saveOrUpdate(Order order) {
        this.addressRepository.save(order.getAddress());
        return this.orderRespository.save(order);
    }

}
