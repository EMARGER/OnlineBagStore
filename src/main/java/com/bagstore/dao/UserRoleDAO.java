package com.bagstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bagstore.dto.UserRoleDTO;
import com.bagstore.util.DBUtil;

public class UserRoleDAO {

	private DBUtil dbUtil;

	private final String Q_UserRole_Insert = "insert into user_role (user_id,role_id) values(?,?)";
	private final String Q_UserRole_Find_ALL_User_By_RoleName = "select * from user u left join user_role ur on u.id = ur.user_id inner join role r on ur.role_id = r.id where r.name = ?";
	private final String Q_UserRole_Find_All_Role_By_UserName = "select * from user u left join user_role ur on u.id = ur.user_id inner join role r on ur.role_id = r.id where u.name = ?";
	private final String Q_UserRole_Find_All = "select * from user u left join user_role ur on u.id = ur.user_id inner join role r on ur.role_id = r.id ";

	public UserRoleDAO(DBUtil dbUtil) {
		this.dbUtil = dbUtil;
	}

	public int save(UserRoleDTO userRoleDTO) throws Exception {

		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = dbUtil.getConnection();
			statement = connection.prepareStatement(Q_UserRole_Insert);

			statement.setInt(1, userRoleDTO.getUserId());
			statement.setInt(2, userRoleDTO.getRoleId());

			int count = statement.executeUpdate();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			dbUtil.close(connection, statement);
		}

	}
	
	public List<UserRoleDTO> findUsersByRoleName(String roleName) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = dbUtil.getConnection();
			statement = connection.prepareStatement(Q_UserRole_Find_ALL_User_By_RoleName);
			
			statement.setString(1, roleName);
			ResultSet rs = statement.executeQuery();
			UserRoleDTO userRoleDTO = null;
			List<UserRoleDTO> userRoleDTOs = new ArrayList<>();
			while (rs.next()) {
				userRoleDTO = new UserRoleDTO();
				userRoleDTO.setId(rs.getInt("id"));
				userRoleDTO.setUserId(rs.getInt("user_id"));
				userRoleDTO.setRoleId(rs.getInt("role_id"));
				
				userRoleDTOs.add(userRoleDTO);
			}
			return userRoleDTOs;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			dbUtil.close(connection, statement);
		}
		
	}
	public List<UserRoleDTO> findRolesByUserName(String userName) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = dbUtil.getConnection();
			statement = connection.prepareStatement(Q_UserRole_Find_All_Role_By_UserName);
			
			statement.setString(1, userName);
			ResultSet rs = statement.executeQuery();
			UserRoleDTO userRoleDTO = null;
			List<UserRoleDTO> userRoleDTOs = new ArrayList<>();
			while (rs.next()) {
				userRoleDTO = new UserRoleDTO();
				userRoleDTO.setId(rs.getInt("id"));
				userRoleDTO.setUserId(rs.getInt("user_id"));
				userRoleDTO.setRoleId(rs.getInt("role_id"));
				
				userRoleDTOs.add(userRoleDTO);
			}
			return userRoleDTOs;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			dbUtil.close(connection, statement);
		}
		
	}
	public List<UserRoleDTO> findAll() throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = dbUtil.getConnection();
			statement = connection.prepareStatement(Q_UserRole_Find_All);
			ResultSet rs = statement.executeQuery();
			UserRoleDTO userRoleDTO = null;
			List<UserRoleDTO> userRoleDTOs = new ArrayList<>();
			while (rs.next()) {
				userRoleDTO = new UserRoleDTO();
				userRoleDTO.setId(rs.getInt("id"));
				userRoleDTO.setUserId(rs.getInt("user_id"));
				userRoleDTO.setRoleId(rs.getInt("role_id"));
				
				userRoleDTOs.add(userRoleDTO);
			}
			return userRoleDTOs;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			dbUtil.close(connection, statement);
		}
		
	}

}
