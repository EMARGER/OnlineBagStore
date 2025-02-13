package com.bagstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bagstore.dto.OrderItemDTO;
import com.bagstore.util.DBUtil;

public class OrderItemDAO {
	private final String Q_SAVE_ORDER_ITEM = "insert into order_item(purchase_order_id,product_id,quantity,price) values(?,?,?,?)";
	private final String Q_DELETE_ORDER_ITEM = "delete from order_item where id=?";
	private final String Q_FIND_BY_ID = "select * from order_item where id=?";
	private final String Q_FIND_ALL = "select * from order_item";

	DBUtil dbUtil = new DBUtil();

	public OrderItemDAO(DBUtil dbUtil) {
		this.dbUtil = dbUtil;
	}

	Connection connection = null;
	PreparedStatement pstmt = null;

	public int saveOrderItem(OrderItemDTO orderItemDTO) throws Exception {
		try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(Q_SAVE_ORDER_ITEM);
			pstmt.setInt(1, orderItemDTO.getPurchasOrderId());
			pstmt.setInt(2, orderItemDTO.getProductId());
			pstmt.setInt(3, orderItemDTO.getQuantity());
			pstmt.setDouble(4, orderItemDTO.getTotalPrice());
			int count = pstmt.executeUpdate();
			return count;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		} finally {
			dbUtil.close(connection, pstmt);
		}
	}

	public int deleteOrderItem(Integer id) throws Exception {
		try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(Q_DELETE_ORDER_ITEM);
			pstmt.setInt(1, id);
			int count = pstmt.executeUpdate();
			return count;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		} finally {
			dbUtil.close(connection, pstmt);
		}
	}

	public OrderItemDTO findOrderItemById(Integer id) throws Exception {
		try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(Q_FIND_BY_ID);
			pstmt.setInt(1, id);
			OrderItemDTO orderItemDTO = null;
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				orderItemDTO = new OrderItemDTO();
				orderItemDTO.setId(rs.getInt("id"));
				orderItemDTO.setPurchasOrderId(rs.getInt("purchase_order_id"));
				orderItemDTO.setProductId(rs.getInt("product_id"));
				orderItemDTO.setQuantity(rs.getInt("quantity"));
				orderItemDTO.setTotalPrice(rs.getDouble("price"));
			}
			return orderItemDTO;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}finally {
			dbUtil.close(connection, pstmt);
		}
	}
	public List<OrderItemDTO> findAllOrderItem() throws Exception {
		try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(Q_FIND_ALL);
			OrderItemDTO orderItemDTO = null;
			List<OrderItemDTO> orderItemDTOs= new ArrayList<>();
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				orderItemDTO = new OrderItemDTO();
				orderItemDTO.setId(rs.getInt("id"));
				orderItemDTO.setPurchasOrderId(rs.getInt("purchase_order_id"));
				orderItemDTO.setProductId(rs.getInt("product_id"));
				orderItemDTO.setQuantity(rs.getInt("quantity"));
				orderItemDTO.setTotalPrice(rs.getDouble("price"));
				orderItemDTOs.add(orderItemDTO);

			}
			return orderItemDTOs;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}finally {
			dbUtil.close(connection, pstmt);
		}
	}

}

