package com.bagstore.servlet;

import java.util.List;

import com.bagstore.dao.OrderDAO;
import com.bagstore.dto.OrderDTO;
import com.bagstore.service.OrderService;
import com.bagstore.util.DBUtil;

public class OrderServletMain {

	private OrderService orderService;

	public OrderServletMain(OrderService orderService) {
		this.orderService = orderService;
	}
	public void save() {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setUserId(6);
		orderDTO.setTotalPrice(1000);
		orderDTO.setAddress("Khndwa Naka");
		orderDTO.setCity("Indore");
		orderDTO.setPincode(452020);
		orderDTO.setPaymentMode("Cash On Delievery");
		try {
			int orderId = orderService.save(orderDTO);
			System.out.println("orderId : "+ orderId);
			if(orderId > 0) {
				System.out.println("Order Saved Succesfully");
			}
			else {
				System.out.println("Failed to saved Order");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	public void findAllOrderByUserId() {
		try {
			List<OrderDTO> orderDTOs = orderService.findAllOrdersByUserId(6);
			if(orderDTOs != null) {
				System.out.println(orderDTOs);
			}
			else {
				System.out.println("Failed to saved Order");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		DBUtil dbUtil = new DBUtil();
		OrderDAO orderDAO = new OrderDAO(dbUtil);
		OrderService orderService = new OrderService(orderDAO);
		OrderServletMain orderServletMain = new OrderServletMain(orderService);
//		orderServletMain.save();
		orderServletMain.findAllOrderByUserId();
	}
	
	
}
