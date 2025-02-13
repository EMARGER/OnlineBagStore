package com.bagstore.service;

import java.util.List;

import com.bagstore.dao.RoleDAO;
import com.bagstore.dto.RoleDTO;

public class RoleService {
	private RoleDAO roleDAO;
	
	public RoleService(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}
	public int save(RoleDTO roleDTO) throws Exception {
		return roleDAO.save(roleDTO);
	}
	public int delete(int id) throws Exception {
		return roleDAO.delete(id);
	}
	public RoleDTO findRoleById(int id) throws Exception {
		return roleDAO.findRoleById(id);
	}
	public RoleDTO findRoleByName(String name) throws Exception {
		return roleDAO.findRoleByName(name);
	}
	public RoleDTO findDefaultRole() throws Exception {
		return roleDAO.findDefaultRole();
	}
	public List<RoleDTO> findAllRole() throws Exception {
		return roleDAO.findAllRole();
	}
}
