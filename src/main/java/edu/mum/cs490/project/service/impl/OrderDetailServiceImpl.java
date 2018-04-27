package edu.mum.cs490.project.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import edu.mum.cs490.project.domain.*;
import edu.mum.cs490.project.repository.*;
import edu.mum.cs490.project.service.*;

@Service("orderDetailService")
@Transactional
public class OrderDetailServiceImpl implements OrderDetailService {

	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Override
	public List<OrderDetail> findByOrder_OrderDate(Date from, Date to) {
//		return orderDetailRepository.findByOrder_OrderDate(from, to);
		return null;
	}

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
			//item.put("orderDate", orderDetail.getOrder().getOrderDate());
//			item.put("customer", orderDetail.getOrder().getCustomer());
//			item.put("vendor", orderDetail.getProduct().getVendor());
//			item.put("price", orderDetail.getPrice());
			item.put("product", orderDetail.getProduct().getName());
			item.put("quantity", orderDetail.getQuantity());
			result.add(item);
		}
		return result;
	}

}
