package edu.mum.cs490.project.service;

import java.util.*;

import edu.mum.cs490.project.domain.*;

public interface OrderDetailService {
	List<OrderDetail> findAll();
	List<Map<String, Object>> report();
}
