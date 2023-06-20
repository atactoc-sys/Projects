package com.pms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pms.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	@Query("select order from Order order where order.oname = ?1")
	List<Order> getOname(String oname);
	
}
