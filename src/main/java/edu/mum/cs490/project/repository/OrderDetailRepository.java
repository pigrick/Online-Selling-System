package edu.mum.cs490.project.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.*;

import edu.mum.cs490.project.domain.*;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer>{
	
	List<OrderDetail> findByOrder_OrderDate(Date from, Date to);
	
//	Query=()
//	List<Object> listResultReport();
	
}
