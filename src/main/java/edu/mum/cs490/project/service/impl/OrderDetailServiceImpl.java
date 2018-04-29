package edu.mum.cs490.project.service.impl;

import edu.mum.cs490.project.domain.OrderDetail;
import edu.mum.cs490.project.repository.OrderDetailRepository;
import edu.mum.cs490.project.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("orderDetailService")
@Transactional
public class OrderDetailServiceImpl implements OrderDetailService {

	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Override
	public List<OrderDetail> findAll() {
		return orderDetailRepository.findAll();
	}

	@Override
	public List<Map<String, Object>> report() {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for(OrderDetail orderDetail: this.findAll()) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("id", orderDetail.getId());
			item.put("orderDate", orderDetail.getOrder().getOrderDate());
			item.put("customer", orderDetail.getOrder().getCustomer());
			item.put("vendor", orderDetail.getProduct().getVendor());
			item.put("price", orderDetail.getPrice());
			item.put("product", orderDetail.getProduct().getName());
			item.put("quantity", orderDetail.getQuantity());
			result.add(item);
		}
		return result;
	}

	@Override
	public List<OrderDetail> findByOrder_OrderDate(Date from, Date to) {
		List<OrderDetail> orderDetails = orderDetailRepository.findAll();
		return orderDetails;
	}


}
