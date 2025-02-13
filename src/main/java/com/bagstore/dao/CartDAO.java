package com.bagstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bagstore.dto.CartDTO;
import com.bagstore.util.DBUtil;

public class CartDAO {
	private final String Q_ADD_TO_CART = "insert into cart(user_id,product_id,quantity) values(?,?,?)";
	private final String Q_UPDATE_CART = "update cart  set id=?,user_id=?,product_id=?,quantity=? where id=?";
	private final String Q_FIND_ALL = "select * from cart";
	private final String Q_FIND_BY_ID = "select * from cart where id=?";
	private final String Q_DELETE_by_id = "delete from cart where id=?";

	DBUtil dbUtil = null;

	public CartDAO(DBUtil dbUtil) {
		this.dbUtil = dbUtil;
	}

	Connection connection = null;
	PreparedStatement pstmt = null;

	public int addToCart(CartDTO cartDTO) throws SQLException {

		try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(Q_ADD_TO_CART);

			pstmt.setInt(1, cartDTO.getUserId());
			pstmt.setInt(2, cartDTO.getProductId());
			pstmt.setInt(3, cartDTO.getQuntity());
		 int count = pstmt.executeUpdate();
			return count;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		} finally {
			dbUtil.close(connection, pstmt);

		}
		return 0;

	}

	public int updateCart(CartDTO cartDTO) throws SQLException {

		try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(Q_UPDATE_CART);

			pstmt.setInt(1, cartDTO.getUserId());
			pstmt.setInt(2, cartDTO.getProductId());
			pstmt.setInt(3, cartDTO.getQuntity());
	
			pstmt.setInt(4, cartDTO.getId());

			int count = pstmt.executeUpdate();
			return count;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		} finally {
			dbUtil.close(connection, pstmt);

		}
		return 0;

	}

	public int deleteToCart(Integer id) throws SQLException {

		try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(Q_DELETE_by_id);

			pstmt.setInt(1, id);
			int count = pstmt.executeUpdate();
			return count;
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			dbUtil.close(connection, pstmt);

		}
		return 0;

	}

	
	public List<CartDTO> findAllCart() throws Exception {
		
		try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(Q_FIND_ALL);
			CartDTO cartDTO = null;
			List<CartDTO> cartDTOs = new ArrayList<>();
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				cartDTO = new CartDTO();
				cartDTO.setId(rs.getInt("id"));
				cartDTO.setUserId(rs.getInt("user_id"));
				cartDTO.setProductId(rs.getInt("product_id"));
				cartDTO.setQuntity(rs.getInt("quantity"));
				cartDTOs.add(cartDTO);

			}
		return cartDTOs;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;

		} finally {
			dbUtil.close(connection, pstmt);

		}
	}
public CartDTO findCartById(int id) throws Exception {
		
		try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(Q_FIND_BY_ID);
			
			pstmt.setInt(1, id);
			CartDTO cartDTO = null;
			 
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				cartDTO = new CartDTO();
				cartDTO.setId(rs.getInt("id"));
				cartDTO.setUserId(rs.getInt("user_id"));
				cartDTO.setProductId(rs.getInt("product_id"));
				cartDTO.setQuntity(rs.getInt("quantity"));
				

			}
		return cartDTO;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;

		} finally {
			dbUtil.close(connection, pstmt);

		}
	}

}
