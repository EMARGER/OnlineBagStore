package com.bagstore.service;

import java.util.List;


import com.bagstore.dao.UserDAO;
import com.bagstore.dto.UserDTO;

public class UserService {
	private UserDAO userDAO;

	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	public int save(UserDTO userDTO) throws Exception {
		return userDAO.save(userDTO);
	}
	public int update(UserDTO userDTO) throws Exception {
		return userDAO.update(userDTO);
	}
	public UserDTO login(String username, String password) throws Exception {
		return userDAO.login(username, password);
	}
	public UserDTO findById(int id) throws Exception {
		return userDAO.fiindById(id);
	}
	public int delete(int id) throws Exception {
		return userDAO.delete(id);
	}
	public List<UserDTO> findAll() throws Exception {
		return userDAO.findAll();
	}
}
