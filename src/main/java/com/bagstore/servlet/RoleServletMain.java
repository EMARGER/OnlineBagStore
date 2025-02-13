package com.bagstore.servlet;

import java.util.List;

import com.bagstore.dao.RoleDAO;
import com.bagstore.dao.UserDAO;
import com.bagstore.dto.RoleDTO;
import com.bagstore.dto.UserDTO;
import com.bagstore.service.RoleService;
import com.bagstore.service.UserService;
import com.bagstore.util.DBUtil;

public class RoleServletMain {
	
	public RoleService roleService;
	
	public RoleServletMain(RoleService roleService) {
		this.roleService = roleService;
	}
	public void save() {
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setName("Delivery Partner");
		roleDTO.setDescription("responsible related to delivery of product");
		
		try {
			int count = roleService.save(roleDTO);
			
			if(count > 0) {
				System.out.println("Role Saved Succesfully");
			}
			else {
				System.out.println("Failed to saved role");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void delete() {
		int id  = 4;
		
		try {
			int count = roleService.delete(id);
			
			if(count > 0) {
				System.out.println("Role Delete Succesfully");
			}
			else {
				System.out.println("Failed to Delete role");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void findRoleById() {
		int id = 1;
		try {
			RoleDTO roleDTO = roleService.findRoleById(id);
			
			if(roleDTO != null) {
				System.out.println("Role found");
				System.out.println(roleDTO.getName() + " " + roleDTO.getDescription() + " " + roleDTO.getId());
			}
			else {
				System.out.println("user not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void findRoleByName() {
		String name = "Admin";
		try {
			RoleDTO roleDTO = roleService.findRoleByName(name);
			
			if(roleDTO != null) {
				System.out.println("Role found");
				System.out.println(roleDTO.getName() + " " + roleDTO.getDescription() + " " + roleDTO.getId());
			}
			else {
				System.out.println("user not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void findDefaultRole() {
		
		try {
			RoleDTO roleDTO = roleService.findDefaultRole();
			
			if(roleDTO != null) {
				System.out.println("Role found");
				System.out.println(roleDTO.getName() + " " + roleDTO.getDescription() + " " + roleDTO.getId());
			}
			else {
				System.out.println("user not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void findAllRole() {
		
		try {
			List<RoleDTO> roleDTOs = roleService.findAllRole();
			
			if(roleDTOs != null) {
				System.out.println("Role found");
				for (RoleDTO roleDTO : roleDTOs) {
					System.out.println(roleDTO.getName() + " " + roleDTO.getDescription() + " " + roleDTO.getId());
				}
			}
			else {
				System.out.println("user not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		DBUtil dbUtil = new DBUtil();
		RoleDAO roleDAO = new RoleDAO(dbUtil);
		RoleService roleService = new RoleService(roleDAO);
		RoleServletMain mainServlet = new RoleServletMain(roleService);
//		mainServlet.findDefaultRole();
//		mainServlet.findRoleById();
//		mainServlet.findAllRole();
//		mainServlet.findRoleByName();
		mainServlet.save();
//		mainServlet.delete();
		
		
	}
}
