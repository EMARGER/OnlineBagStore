package com.bagstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bagstore.dto.ProductDTO;
import com.bagstore.util.DBUtil;

public class ProductDAO {
	private final String Q_SAVE_PRODUCT = "insert into product(name,price,"
			+ "quantity_available,description,category_id) " + "value(?,?,?,?,?)";
	private final String Q_UPDATE_BY_ID = "update product set name=?,price=?,quantity_available=?,description=?,category_id=? where id=?";
	private final String Q_DELETE_BY_ID = "delete from product where id=?";
	private final String Q_FIND_BY_ID = "select * from product where id=? ";
	private final String Q_FIND_BY_Category_ID = "select * from product where category_id=? ";
	private final String Q_FIND_ALL = "select * from product ";
	DBUtil dbUtil = new DBUtil();

	public ProductDAO(DBUtil dbUtil) {
		this.dbUtil = dbUtil;

	}

	Connection connection = null;
	PreparedStatement pstmt = null;

	public int addProductInfo(ProductDTO productDTO) throws Exception {

		try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(Q_SAVE_PRODUCT);

			pstmt.setString(1, productDTO.getName());
			pstmt.setDouble(2, productDTO.getPrice());
			pstmt.setInt(3, productDTO.getQuantityAvailable());
			pstmt.setString(4, productDTO.getDescription());
			pstmt.setInt(5, productDTO.getCategoryId());
			
			int count = pstmt.executeUpdate();
			return count;

		} catch (Exception e) {

			e.printStackTrace();
			// TODO: handle exception
			throw e;
		} finally {
			dbUtil.close(connection, pstmt);
		}

	}

	public int updateProductById(ProductDTO productDTO) throws Exception {

		try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(Q_UPDATE_BY_ID);

			pstmt.setString(1, productDTO.getName());
			pstmt.setDouble(2, productDTO.getPrice());
			pstmt.setInt(3, productDTO.getQuantityAvailable());
			pstmt.setString(4, productDTO.getDescription());
			pstmt.setInt(5, productDTO.getCategoryId());
			pstmt.setInt(6, productDTO.getId());
			int count = pstmt.executeUpdate();
			return count;

		} catch (Exception e) {

			e.printStackTrace();
			// TODO: handle exception
			throw e;
		} finally {
			dbUtil.close(connection, pstmt);
		}

	}

	public int deleteProductById(Integer id) throws Exception {

		try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(Q_DELETE_BY_ID);

			pstmt.setInt(1, id);

			int count = pstmt.executeUpdate();
			return count;

		} catch (Exception e) {

			e.printStackTrace();
			// TODO: handle exception
			throw e;
		} finally {
			dbUtil.close(connection, pstmt);
		}

	}

	public ProductDTO findProductById(Integer id) throws Exception {

		try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(Q_FIND_BY_ID);
			pstmt.setInt(1, id);
			ProductDTO productDTO = null;
		
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				productDTO = new ProductDTO();
				productDTO.setId(rs.getInt("id"));
				productDTO.setName(rs.getString("name"));
				productDTO.setDescription(rs.getString("description"));
				productDTO.setPrice(rs.getDouble("price"));
				productDTO.setQuantityAvailable(rs.getInt("quantity_available"));
				productDTO.setCategoryId(rs.getInt("category_id"));

			}
			return productDTO;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;

		} finally {
			dbUtil.close(connection, pstmt);

		}
	}
	public List<ProductDTO> findProductByCategoryId(Integer categoryId) throws Exception {

		try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(Q_FIND_BY_Category_ID);
			pstmt.setInt(1, categoryId);
			
			ProductDTO productDTO = null;
			List<ProductDTO> productDTOs = new ArrayList<>();
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				productDTO = new ProductDTO();
				productDTO.setId(rs.getInt("id"));
				productDTO.setName(rs.getString("name"));
				productDTO.setDescription(rs.getString("description"));
				productDTO.setPrice(rs.getDouble("price"));
				productDTO.setQuantityAvailable(rs.getInt("quantity_available"));
				productDTO.setCategoryId(rs.getInt("category_id"));
				productDTOs.add(productDTO);

			}
			return productDTOs;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;

		} finally {
			dbUtil.close(connection, pstmt);

		}
	}

	public List<ProductDTO> findAllProduct() throws Exception {

		try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(Q_FIND_ALL);
			ProductDTO productDTO = null;
			List<ProductDTO> productDTOs = new ArrayList<>();
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				productDTO = new ProductDTO();
				productDTO.setId(rs.getInt("id"));
				productDTO.setName(rs.getString("name"));
				productDTO.setDescription(rs.getString("description"));
				productDTO.setPrice(rs.getDouble("price"));
				productDTO.setQuantityAvailable(rs.getInt("quantity_available"));
			
				productDTO.setCategoryId(rs.getInt("category_id"));
				productDTOs.add(productDTO);

			}
			return productDTOs;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;

		} finally {
			dbUtil.close(connection, pstmt);

		}
	}
}
