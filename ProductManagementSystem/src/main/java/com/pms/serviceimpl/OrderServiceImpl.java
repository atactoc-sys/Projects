package com.pms.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.entity.Order;
import com.pms.exception.OrderNotFoundException;
import com.pms.repository.OrderRepository;
import com.pms.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderRepository or;

	@Override
	public Order placedOrder(Order order) {
		
		return or.save(order);
		
	}

	@Override
	public List<Order> getAllOrders() {
		
		return or.findAll();
	}

	@Override
	public Order getOrderById(int oid) {
		
		return or.findById(oid).orElseThrow(() -> new OrderNotFoundException("No order exist in table"));
	}

	@Override
	public void deleteOrderById(int oid) {
		
		or.findById(oid).orElseThrow(() -> new OrderNotFoundException("No order exist in table"));
		or.deleteById(oid);
		
	}

	@Override
	public List<Order> getOname(String oname) {

		return or.getOname(oname);
	}

}
