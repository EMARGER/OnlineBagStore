package com.bagstore.service;

import java.util.List;

import com.bagstore.dao.UserRoleDAO;
import com.bagstore.dto.UserRoleDTO;

public class UserRoleService {
	
	private UserRoleDAO userRoleDAO;
	
	public UserRoleService(UserRoleDAO userRoleDAO) {
		this.userRoleDAO = userRoleDAO;
	}
	
	public int save(UserRoleDTO userRoleDTO) throws Exception {
		return userRoleDAO.save(userRoleDTO);
	}
	
	public List<UserRoleDTO> findUsersByRoleName(String roleName) throws Exception {
		return userRoleDAO.findUsersByRoleName(roleName);
	}
	
	public List<UserRoleDTO> findRolesByUserName(String userName) throws Exception {
		return userRoleDAO.findRolesByUserName(userName);
	}
	
	public List<UserRoleDTO> findAll()throws Exception {
		return userRoleDAO.findAll();
	}
}
