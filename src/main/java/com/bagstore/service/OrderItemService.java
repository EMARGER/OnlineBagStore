package com.bagstore.service;

import java.util.List;

import com.bagstore.dao.OrderItemDAO;
import com.bagstore.dto.OrderItemDTO;

public class OrderItemService {

	private OrderItemDAO orderItemDAO;
	public OrderItemService(OrderItemDAO orderItemDAO) {
		this.orderItemDAO=orderItemDAO;
	}
	
	public int saveOrderItem(OrderItemDTO orderItemDTO) throws Exception {
		int count= orderItemDAO.saveOrderItem(orderItemDTO);
		return count;
	}
	
	public int deleteOrderItem(Integer id) throws Exception {
		int count= orderItemDAO.deleteOrderItem(id);
		return count;
	}
	public OrderItemDTO findOrderItemByID(Integer ID) throws Exception {
		return orderItemDAO.findOrderItemById(ID);
	}
	public List<OrderItemDTO> findAllOrderItem() throws Exception {
		return orderItemDAO.findAllOrderItem();
		
	}
	public List<OrderItemDTO> findOrderItemByOrderId(Integer orderId) throws Exception {
		return orderItemDAO.findOrderItemByOrderId(orderId);
	}
}
