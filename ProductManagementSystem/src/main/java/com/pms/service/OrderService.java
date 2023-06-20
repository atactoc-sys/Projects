package com.pms.service;

import java.util.List;

import com.pms.entity.Order;

public interface OrderService {
	
	// saving details of order entity
	Order placedOrder(Order order);
	
	// getting all details of order
	List<Order> getAllOrders();
	
	// getting details of order based on oid
	Order getOrderById(int oid);
	
	// getting details of order based of oid & delete mentioned details
	void deleteOrderById(int oid);
	
	List<Order> getOname(String oname);

}
