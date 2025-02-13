package com.bagstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bagstore.dto.CategoryDTO;

import com.bagstore.util.DBUtil;

public class CategoryDAO {
	private DBUtil dbUtil;
	private final String Q_Category_Insert = " insert into category (name, description) values (?,?)";
	private final String Q_Category_Delete_By_Id= "delete from category where id = ?";
	private final String Q_category_Find_All = " select * from category";
	private final String Q_Category_Find_By_Id = "select * from category where id = ?";
	private final String Q_Category_Find_By_Name = "select * from category where name = ?";
	
	public CategoryDAO(DBUtil dbUtil) {
		this.dbUtil = dbUtil;
	}

	public int save(CategoryDTO categoryDTO) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dbUtil.getConnection();

			statement = connection.prepareStatement(Q_Category_Insert);
			statement.setString(1, categoryDTO.getName());
			statement.setString(2, categoryDTO.getDescription());

			int count = statement.executeUpdate();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			dbUtil.close(connection, statement);
		}
	}
	public int delete(int id) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;

		try {

			connection = dbUtil.getConnection();
			statement = connection.prepareStatement(Q_Category_Delete_By_Id);
			statement.setInt(1, id);
			int count = statement.executeUpdate();
			return count;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			dbUtil.close(connection, statement);
		}

	}
	public List<CategoryDTO> findAll() throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = dbUtil.getConnection();
			statement = connection.prepareStatement(Q_category_Find_All);
			ResultSet rs = statement.executeQuery();

			CategoryDTO categoryDTO = null;
			List<CategoryDTO> categoryDTOs = new ArrayList<>();
			while (rs.next()) {
				categoryDTO = new CategoryDTO();
				categoryDTO.setId(rs.getInt("id"));
				categoryDTO.setName(rs.getString("name"));
				categoryDTO.setDescription(rs.getString("description"));
				
				categoryDTOs.add(categoryDTO);
			}
			
			return categoryDTOs;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			dbUtil.close(connection, statement);
		}

	}
	public CategoryDTO findById(int id) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = dbUtil.getConnection();
			statement = connection.prepareStatement(Q_Category_Find_By_Id);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();

			CategoryDTO categoryDTO = null;
			
			if (rs.next()) {
				categoryDTO = new CategoryDTO();
				categoryDTO.setId(rs.getInt("id"));
				categoryDTO.setName(rs.getString("name"));
				categoryDTO.setDescription(rs.getString("description"));
			}
			
			return categoryDTO;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			dbUtil.close(connection, statement);
		}

	}
	
	public CategoryDTO findByName(String name) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = dbUtil.getConnection();
			statement = connection.prepareStatement(Q_Category_Find_By_Name);
			statement.setString(1, name);
			ResultSet rs = statement.executeQuery();

			CategoryDTO categoryDTO = null;
			
			if (rs.next()) {
				categoryDTO = new CategoryDTO();
				categoryDTO.setId(rs.getInt("id"));
				categoryDTO.setName(rs.getString("name"));
				categoryDTO.setDescription(rs.getString("description"));
			}
			
			return categoryDTO;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			dbUtil.close(connection, statement);
		}

	}
}
