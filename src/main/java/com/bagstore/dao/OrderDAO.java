package com.bagstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bagstore.dto.CartDTO;
import com.bagstore.dto.FeedbackDTO;
import com.bagstore.dto.OrderDTO;
import com.bagstore.util.DBUtil;

public class OrderDAO {
	private DBUtil dbUtil;

	private final String Q_Order_Insert = "insert into orders (user_id,total_price,payment_mode,address,city,pincode) values (?,?,?,?,?,?)";
	private final String Q_FIND_BY_User_ID = "select * from orders where user_id=?";

	public OrderDAO(DBUtil dbUtil) {
		this.dbUtil = dbUtil;
	}

	public int save(OrderDTO orderDTO) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet generatedKeys = null;
		int orderId = 0;

		try {
			connection = dbUtil.getConnection();
			statement = connection.prepareStatement(Q_Order_Insert, Statement.RETURN_GENERATED_KEYS); // ✅ Fix

			statement.setInt(1, orderDTO.getUserId());
			statement.setDouble(2, orderDTO.getTotalPrice());
			statement.setString(3, orderDTO.getPaymentMode());
			statement.setString(4, orderDTO.getAddress());
			statement.setString(5, orderDTO.getCity());
			statement.setInt(6, orderDTO.getPincode());

			int count = statement.executeUpdate();

			if (count > 0) {
				generatedKeys = statement.getGeneratedKeys();
				if (generatedKeys.next()) {
					orderId = generatedKeys.getInt(1); // ✅ Correct way to retrieve Order ID
				}
			}

			return orderId; // Return Order ID instead of count

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (generatedKeys != null)
				generatedKeys.close();
			dbUtil.close(connection, statement); // ✅ Fix: Close ResultSet properly
		}
	}

	public List<OrderDTO> findAllOrdersByUserId(int userId) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dbUtil.getConnection();
			statement = connection.prepareStatement(Q_FIND_BY_User_ID);
			statement.setInt(1, userId);
			OrderDTO orderDTO = null;
			List<OrderDTO> orderDTOs = new ArrayList<OrderDTO>();
			ResultSet rs = statement.executeQuery();
			
			
			
			while (rs.next()) {
				orderDTO = new OrderDTO();
				orderDTO.setId(rs.getInt("id"));
				orderDTO.setUserId(rs.getInt("user_id"));
				orderDTO.setTotalPrice(rs.getInt("total_price"));
				orderDTO.setAddress(rs.getString("address"));
				orderDTO.setCity(rs.getString("city"));
				orderDTO.setPincode(rs.getInt("pincode"));
				orderDTO.setPaymentMode(rs.getString("payment_mode"));
				orderDTOs.add(orderDTO);
				
			}
			
			return orderDTOs;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			dbUtil.close(connection, statement);
		}
	}

}
