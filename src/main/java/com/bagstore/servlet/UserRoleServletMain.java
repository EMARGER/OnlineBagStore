package com.bagstore.servlet;

import java.util.List;

import com.bagstore.dao.UserRoleDAO;
import com.bagstore.dto.UserRoleDTO;
import com.bagstore.service.UserRoleService;
import com.bagstore.util.DBUtil;

public class UserRoleServletMain {
	private UserRoleService userRoleService;

	public UserRoleServletMain(UserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}

	public void save() {
		UserRoleDTO userRoleDTO = new UserRoleDTO();
		userRoleDTO.setRoleId(2);
		userRoleDTO.setUserId(14);
		
		try {
			
			int count = userRoleService.save(userRoleDTO);
			
			if(count>0) {
				System.out.println("Role assigned to User Successfully");
			}
			else {
				System.out.println("Failed to assigned Role to User");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void findUsersByRoleName() {
		 String roleName = "Admin";
		 try {
			List<UserRoleDTO> userRoleDTOs = userRoleService.findUsersByRoleName(roleName);
			if(userRoleDTOs != null) {
				System.out.println("User Roles Are Found");
				for (UserRoleDTO userRoleDTO : userRoleDTOs) {
					System.out.println("id :" + userRoleDTO.getId());
					System.out.println("User-Id :" + userRoleDTO.getUserId());
					System.out.println("Role-Id :" + userRoleDTO.getRoleId());
				}
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	public void findRolesByUserName() {
		 String userName = "Atul Patel";
		 try {
			List<UserRoleDTO> userRoleDTOs = userRoleService.findRolesByUserName(userName);
			if(userRoleDTOs != null) {
				System.out.println("User Roles Are Found");
				for (UserRoleDTO userRoleDTO : userRoleDTOs) {
					System.out.println("id :" + userRoleDTO.getId());
					System.out.println("User-Id :" + userRoleDTO.getUserId());
					System.out.println("Role-Id :" + userRoleDTO.getRoleId());
				}
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	public void findAll() {
		 try {
			List<UserRoleDTO> userRoleDTOs = userRoleService.findAll();
			if(userRoleDTOs != null) {
				System.out.println("User Roles Are Found");
				for (UserRoleDTO userRoleDTO : userRoleDTOs) {
					System.out.println("236");
					System.out.println("id :" + userRoleDTO.getId());
					System.out.println("User-Id :" + userRoleDTO.getUserId());
					System.out.println("Role-Id :" + userRoleDTO.getRoleId());
				}
			}
			else {
				System.out.println("not found");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		 
		 
		
	}
	public static void main(String[] args) {
		DBUtil dbUtil = new DBUtil();
		UserRoleDAO userRoleDAO = new UserRoleDAO(dbUtil);
		UserRoleService userRoleService = new UserRoleService(userRoleDAO);
		UserRoleServletMain mainServlet = new UserRoleServletMain(userRoleService);
//		mainServlet.save();
//		mainServlet.findUsersByRoleName();
		mainServlet.findRolesByUserName();
//		mainServlet.findAll();
	}
}
