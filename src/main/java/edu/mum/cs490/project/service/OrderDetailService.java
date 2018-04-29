package edu.mum.cs490.project.service;

import edu.mum.cs490.project.domain.OrderDetail;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderDetailService {
	List<OrderDetail> findAll();
	List<Map<String, Object>> report();
	List<OrderDetail> findByOrder_OrderDate(Date from, Date to);
}
