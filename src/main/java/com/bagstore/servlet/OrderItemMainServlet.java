package com.bagstore.servlet;

import java.util.List;

import com.bagstore.dao.OrderItemDAO;
import com.bagstore.dto.OrderItemDTO;
import com.bagstore.service.OrderItemService;
import com.bagstore.util.DBUtil;

public class OrderItemMainServlet {

	private OrderItemService orderItemService;

	public OrderItemMainServlet(OrderItemService orderItemService) {
		this.orderItemService = orderItemService;

	}

	public void saveOrderItem() {
		OrderItemDTO orderItemDTO = new OrderItemDTO();

		orderItemDTO.setOrdersId(1);
		orderItemDTO.setProductId(3);
		orderItemDTO.setQuantity(7);
		orderItemDTO.setTotalPrice(4900);
		try {
			int count = orderItemService.saveOrderItem(orderItemDTO);
			if (count > 0) {
				System.out.println("Save Order successfully");
			} else {
				System.out.println("Save to failed");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void deleteOrderItem() {
		Integer id = 2;

		try {
			int count = orderItemService.deleteOrderItem(id);
			if (count > 0) {
				System.out.println("Delete Order successfully");
			} else {
				System.out.println("Delete to failed");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void findOrderItemById() {
		Integer id = 1;

		try {
			OrderItemDTO orderItemDTO = orderItemService.findOrderItemByID(id);
			if (orderItemDTO != null) {
				System.out.println(" Order Item Id :" + orderItemDTO.getId());
				System.out.println(" Purchase Order Item Id :" + orderItemDTO.getOrdersId());
				System.out.println(" Product Item Id :" + orderItemDTO.getProductId());
				System.out.println(" Order Item Quuantity :" + orderItemDTO.getQuantity());
				System.out.println(" Total Price ofOrder Item :" + orderItemDTO.getTotalPrice());
			} else {
				System.out.println("find to failed");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void findAllOrderItem() {
		

		try {
			List<OrderItemDTO> orderItemDTOs = orderItemService.findAllOrderItem();
			if (orderItemDTOs != null) {
				for (OrderItemDTO orderItemDTO : orderItemDTOs) {
					System.out.println(" Order Item Id :" + orderItemDTO.getId());
					System.out.println(" Purchase Order Item Id :" + orderItemDTO.getOrdersId());
					System.out.println(" Product Item Id :" + orderItemDTO.getProductId());
					System.out.println(" Order Item Quuantity :" + orderItemDTO.getQuantity());
					System.out.println(" Total Price ofOrder Item :" + orderItemDTO.getTotalPrice());
				}
			} else {
				System.out.println("find to failed");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void findAllOrderItemByOrderId() {
		

		try {
			List<OrderItemDTO> orderItemDTOs = orderItemService.findOrderItemByOrderId(9);
			if (orderItemDTOs != null) {
				for (OrderItemDTO orderItemDTO : orderItemDTOs) {
					System.out.println(" Order Item Id :" + orderItemDTO.getId());
					System.out.println(" Purchase Order Item Id :" + orderItemDTO.getOrdersId());
					System.out.println(" Product Item Id :" + orderItemDTO.getProductId());
					System.out.println(" Order Item Quuantity :" + orderItemDTO.getQuantity());
					System.out.println(" Total Price ofOrder Item :" + orderItemDTO.getTotalPrice());
					System.out.println("------------------------------");
				}
			} else {
				System.out.println("find to failed");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void main(String[] args) {
		DBUtil dbUtil=new DBUtil();
		OrderItemDAO orderItemDAO= new OrderItemDAO(dbUtil);
		OrderItemService orderItemService= new OrderItemService(orderItemDAO);
		OrderItemMainServlet orderItemMainServlet= new OrderItemMainServlet(orderItemService);
//		orderItemMainServlet.saveOrderItem();
//		orderItemMainServlet.deleteOrderItem();
		//orderItemMainServlet.findOrderItemById();
	    //orderItemMainServlet.findAllOrderItem();
		orderItemMainServlet.findAllOrderItemByOrderId();
	
	}
}
