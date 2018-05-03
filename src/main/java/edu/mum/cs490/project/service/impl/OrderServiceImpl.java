package edu.mum.cs490.project.service.impl;

import edu.mum.cs490.project.domain.Order;
import edu.mum.cs490.project.domain.Status;
import edu.mum.cs490.project.repository.AddressRepository;
import edu.mum.cs490.project.repository.OrderRepository;
import edu.mum.cs490.project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    private static final int PAGE_SIZE = 5;

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
    public Order findById(Integer orderId) {
        return this.orderRespository.findById(orderId).orElse(null);
    }

    @Override
    public Page<Order> findByCustomer_id(Integer customerId, int page) {
        return this.orderRespository.findByCustomer_id(customerId, PageRequest.of(page-1, PAGE_SIZE));
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
