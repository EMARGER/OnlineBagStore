package com.bagstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bagstore.dto.RoleDTO;
import com.bagstore.dto.UserDTO;
import com.bagstore.util.DBUtil;

public class RoleDAO {
	
	private DBUtil dbUtil;
	
	private final String Q_Role_Insert =  "insert into role (name,description) values(?,?)";
	private final String Q_Role_Delete = "delete from role where id = ?"; 
	private final String Q_Role__Find_By_Name = "select * from role where name = ?";
	private final String Q_Role_Find_By_id = "select * form role where id = ?";
	private final String Q_role_FindALL = " select * from role";
	private final String Q_Role_Find_Default_Role =" select * from role where name = 'Customer'";
	
	public RoleDAO(DBUtil dbUtil) {
		this.dbUtil = dbUtil;
	}
	public int save(RoleDTO roleDTO) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dbUtil.getConnection();
			statement = connection.prepareStatement(Q_Role_Insert);
			
			statement.setString(1,roleDTO.getName());
			statement.setString(2,roleDTO.getDescription());
			
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
			statement = connection.prepareStatement(Q_Role_Delete);
			
			statement.setInt(1, id);
			
			int count  = statement.executeUpdate();
			return count;
			
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally {
			dbUtil.close(connection, statement);
		}
	}
	public RoleDTO findRoleById(int id) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dbUtil.getConnection();
			statement = connection.prepareStatement(Q_Role_Find_By_id);
			
			statement.setInt(1, id);
			
			ResultSet rs = statement.executeQuery();
			RoleDTO roleDTO =null;
			if(rs.next()) {
				roleDTO = new RoleDTO();
				roleDTO.setId(rs.getInt("id"));
				roleDTO.setName(rs.getString("name"));
				roleDTO.setDescription(rs.getString("description"));
			}
			return roleDTO;
		} catch (Exception e) {
			
			e.printStackTrace();
			throw e;
		} finally {
			dbUtil.close(connection, statement);
		}
	}
	public RoleDTO findRoleByName(String name) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dbUtil.getConnection();
			statement = connection.prepareStatement(Q_Role__Find_By_Name);
			
			statement.setString(1, name);
			
			ResultSet rs = statement.executeQuery();
			RoleDTO roleDTO =null;
			if(rs.next()) {
				roleDTO = new RoleDTO();
				roleDTO.setId(rs.getInt("id"));
				roleDTO.setName(rs.getString("name"));
				roleDTO.setDescription(rs.getString("description"));
			}
			return roleDTO;
		} catch (Exception e) {
			
			e.printStackTrace();
			throw e;
		} finally {
			dbUtil.close(connection, statement);
		}
	}
	
	public RoleDTO findDefaultRole() throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dbUtil.getConnection();
			statement = connection.prepareStatement(Q_Role_Find_Default_Role);
			
			ResultSet rs = statement.executeQuery();
			RoleDTO roleDTO =null;
			if(rs.next()) {
				roleDTO = new RoleDTO();
				roleDTO.setId(rs.getInt("id"));
				roleDTO.setName(rs.getString("name"));
				roleDTO.setDescription(rs.getString("description"));
			}
			return roleDTO;
		} catch (Exception e) {
			
			e.printStackTrace();
			throw e;
		} finally {
			dbUtil.close(connection, statement);
		}
	}
	
	public List<RoleDTO> findAllRole() throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dbUtil.getConnection();
			statement = connection.prepareStatement(Q_role_FindALL);
			ResultSet rs = statement.executeQuery();
			
			RoleDTO roleDTO =null;
			List<RoleDTO> roleDTOs = new ArrayList<RoleDTO>();
			while(rs.next()) {
				roleDTO = new RoleDTO();
				roleDTO.setId(rs.getInt("id"));
				roleDTO.setName(rs.getString("name"));
				roleDTO.setDescription(rs.getString("description"));
				
				roleDTOs.add(roleDTO);
			}
			return roleDTOs;
		} catch (Exception e) {
			
			e.printStackTrace();
			throw e;
		} finally {
			dbUtil.close(connection, statement);
		}
	}
	
}
