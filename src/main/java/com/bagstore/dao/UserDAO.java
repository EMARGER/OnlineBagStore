package com.bagstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bagstore.dto.UserDTO;
import com.bagstore.util.DBUtil;

public class UserDAO {
	private DBUtil dbUtil;

	private final String Q_User_Save = "insert into user (name, email, phone_number, address, pin_code, city, password) value(?,?,?,?,?,?,?)";
	private final String Q_User_Login = "select * from user where (phone_number = ? or email = ?) and password = ?";
	private final String Q_User_FindById = "select * from user where id = ?";
	private final String Q_User_Update = "update user set name = ?, email = ?, phone_number = ?, address = ?, pin_code = ?, city = ?, password = ? where id = ?";
	private final String Q_User_Delete = "delete from user where id = ?";
	private final String Q_User_FindAll = "select * from user";

	public UserDAO(DBUtil dbUtil) {
		this.dbUtil = dbUtil;
	}

	public int save(UserDTO userDTO) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dbUtil.getConnection();

			statement = connection.prepareStatement(Q_User_Save);
			statement.setString(1, userDTO.getName());
			statement.setString(2, userDTO.getEmail());
			statement.setString(3, userDTO.getPhoneNumber());
			statement.setString(4, userDTO.getAddress());
			statement.setInt(5, userDTO.getPincode());
			statement.setString(6, userDTO.getCity());
			statement.setString(7, userDTO.getPassword());

			int count = statement.executeUpdate();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			dbUtil.close(connection, statement);
		}
	}

	public int update(UserDTO userDTO) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dbUtil.getConnection();

			statement = connection.prepareStatement(Q_User_Update);
			statement.setString(1, userDTO.getName());
			statement.setString(2, userDTO.getEmail());
			statement.setString(3, userDTO.getPhoneNumber());
			statement.setString(4, userDTO.getAddress());
			statement.setInt(5, userDTO.getPincode());
			statement.setString(6, userDTO.getCity());
			statement.setString(7, userDTO.getPassword());
			statement.setInt(8, userDTO.getId());

			int count = statement.executeUpdate();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			dbUtil.close(connection, statement);
		}
	}

	public UserDTO login(String username, String password) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;

		try {

			connection = dbUtil.getConnection();
			statement = connection.prepareStatement(Q_User_Login);

			statement.setString(1, username);
			statement.setString(2, username);
			statement.setString(3, password);

			ResultSet rs = statement.executeQuery();
			UserDTO userDTO = null;
			if (rs.next()) {
				userDTO = new UserDTO();
				userDTO.setId(rs.getInt("id"));
				userDTO.setName(rs.getString("name"));
				userDTO.setEmail(rs.getString("email"));
				userDTO.setPhoneNumber(rs.getString("phone_number"));
				userDTO.setAddress(rs.getString("address"));
				userDTO.setCity(rs.getString("city"));
				userDTO.setPincode(rs.getInt("pin_code"));
				userDTO.setPassword(rs.getString("password"));
				userDTO.setCreatedAt(rs.getDate("created_at"));
			}
			return userDTO;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			dbUtil.close(connection, statement);
		}

	}

	public UserDTO fiindById(int id) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;

		try {

			connection = dbUtil.getConnection();
			statement = connection.prepareStatement(Q_User_FindById);

			statement.setInt(1, id);

			ResultSet rs = statement.executeQuery();
			UserDTO userDTO = null;
			if (rs.next()) {
				userDTO = new UserDTO();
				userDTO.setId(rs.getInt("id"));
				userDTO.setName(rs.getString("name"));
				userDTO.setEmail(rs.getString("email"));
				userDTO.setPhoneNumber(rs.getString("phone_number"));
				userDTO.setAddress(rs.getString("address"));
				userDTO.setCity(rs.getString("city"));
				userDTO.setPincode(rs.getInt("pin_code"));
				userDTO.setPassword(rs.getString("password"));
				userDTO.setCreatedAt(rs.getDate("created_at"));
			}
			return userDTO;
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
			statement = connection.prepareStatement(Q_User_Delete);
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

	public List<UserDTO> findAll() throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = dbUtil.getConnection();
			statement = connection.prepareStatement(Q_User_FindAll);
			ResultSet rs = statement.executeQuery();

			UserDTO userDTO = null;
			List<UserDTO> userDTOs = new ArrayList<>();
			while (rs.next()) {
				userDTO = new UserDTO();
				userDTO.setId(rs.getInt("id"));
				userDTO.setName(rs.getString("name"));
				userDTO.setEmail(rs.getString("email"));
				userDTO.setPhoneNumber(rs.getString("phone_number"));
				userDTO.setAddress(rs.getString("address"));
				userDTO.setCity(rs.getString("city"));
				userDTO.setPincode(rs.getInt("pin_code"));
				userDTO.setPassword(rs.getString("password"));
				userDTO.setCreatedAt(rs.getDate("created_at"));
				
				userDTOs.add(userDTO);
			}
			
			return userDTOs;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			dbUtil.close(connection, statement);
		}

	}

}
