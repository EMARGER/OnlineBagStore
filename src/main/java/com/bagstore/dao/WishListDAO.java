package com.bagstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bagstore.dto.CartDTO;
import com.bagstore.dto.WishListDTO;
import com.bagstore.util.DBUtil;

public class WishListDAO {
	private final String Q_SAVE = "insert into wishlist(product_id,user_id) values(?,?)";
	private final String Q_DELETE = "delete from wishlist where id=?";
	private final String Q_FIND_BY_ID = "select * from wishlist where id=?";
	private final String Q_FIND_BY_User_ID = "select * from wishlist where user_id=?";
	private final String Q_FIND_BY_UserId_ProductId = "select * from wishlist where user_id=? and product_id=?";
	private final String Q_FIND_ALL = "select * from wishlist";

	DBUtil dbUtil = new DBUtil();

	public WishListDAO(DBUtil dbUtil) {
		this.dbUtil = dbUtil;
	}

	Connection connection = null;
	PreparedStatement pstmt = null;

	public int saveToWishlist(WishListDTO wishListDTO) throws Exception {
		try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(Q_SAVE);
			pstmt.setInt(1, wishListDTO.getProductId());
			pstmt.setInt(2, wishListDTO.getUserId());
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

	public int deleteToWishlist(Integer id) throws Exception {
		try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(Q_DELETE);
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

	public WishListDTO findWishListById(Integer id) throws Exception {
		try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(Q_FIND_BY_ID);
			pstmt.setInt(1, id);
			WishListDTO wishListDTO = null;
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				wishListDTO = new WishListDTO();
				wishListDTO.setId(rs.getInt("id"));
				wishListDTO.setProductId(rs.getInt("product_id"));
				wishListDTO.setUserId(rs.getInt("user_id"));
				wishListDTO.setAddedAt(rs.getDate("added_at"));

			}
			return wishListDTO;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		} finally {
			dbUtil.close(connection, pstmt);
		}
	}
	
	public List<WishListDTO> findAllWishListByUserId(int userId) throws Exception {
		try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(Q_FIND_BY_User_ID);
			pstmt.setInt(1, userId);
			WishListDTO wishListDTO = null;
			List<WishListDTO> wishListDTOs = new ArrayList<WishListDTO>();
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				wishListDTO = new WishListDTO();
				wishListDTO.setId(rs.getInt("id"));
				wishListDTO.setProductId(rs.getInt("product_id"));
				wishListDTO.setUserId(rs.getInt("user_id"));
				wishListDTO.setAddedAt(rs.getDate("added_at"));
				wishListDTOs.add(wishListDTO);
			}
			return wishListDTOs;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			dbUtil.close(connection, pstmt);
		}
	}


	public List<WishListDTO> findAllWishList() throws Exception {
		try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(Q_FIND_ALL);
			WishListDTO wishListDTO = null;
			List<WishListDTO> wishListDTOs = new ArrayList<WishListDTO>();
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				wishListDTO = new WishListDTO();
				wishListDTO.setId(rs.getInt("id"));
				wishListDTO.setProductId(rs.getInt("product_id"));
				wishListDTO.setUserId(rs.getInt("user_id"));
				wishListDTO.setAddedAt(rs.getDate("added_at"));
				wishListDTOs.add(wishListDTO);
			}
			return wishListDTOs;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		} finally {
			dbUtil.close(connection, pstmt);
		}
	}
	
	public WishListDTO findWishlistByUserIdProductId(int userId, int productId) throws Exception {

		try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(Q_FIND_BY_UserId_ProductId);

			pstmt.setInt(1, userId);
			pstmt.setInt(2, productId);
			WishListDTO wishListDTO = null;

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				wishListDTO = new WishListDTO();
				wishListDTO.setId(rs.getInt("id"));
				wishListDTO.setUserId(rs.getInt("user_id"));
				wishListDTO.setProductId(rs.getInt("product_id"));

			}
			return wishListDTO;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;

		} finally {
			dbUtil.close(connection, pstmt);

		}
	}


}
