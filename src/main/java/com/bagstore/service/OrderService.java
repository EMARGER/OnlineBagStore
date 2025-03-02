package com.bagstore.service;

import java.util.List;

import com.bagstore.dao.OrderDAO;
import com.bagstore.dto.OrderDTO;
import com.bagstore.dto.OrderItemDTO;

public class OrderService {
	private OrderDAO orderDAO;

	public OrderService(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}
	public int save(OrderDTO orderDTO) throws Exception {
		return orderDAO.save(orderDTO);
	}
	public List<OrderDTO> findAllOrdersByUserId(int userId) throws Exception {
		return orderDAO.findAllOrdersByUserId(userId);
	}
	
	
}
