package edu.mum.cs490.project.service;

import java.util.*;

import edu.mum.cs490.project.domain.*;

public interface OrderDetailService {
	List<OrderDetail> findAll();
	List<OrderDetail> findByOrder_OrderDate(Date from, Date to);
	public List<Map<String, Object>> report();
}
