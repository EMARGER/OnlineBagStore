package com.bagstore.servlet;

import java.sql.SQLException;
import java.util.List;

import com.bagstore.dao.UserDAO;
import com.bagstore.dto.UserDTO;
import com.bagstore.service.UserService;
import com.bagstore.util.DBUtil;

public class UserServletMain {
	private UserService userService;
	public UserServletMain(UserService userService) {
		this.userService = userService;
	}
	
	public void save() {
		UserDTO userDTO = new UserDTO();
		userDTO.setName("Aditya chaudhary");
		userDTO.setEmail("chaudhary@gamil.com");
		userDTO.setAddress("kheda kasoun");
		userDTO.setCity("ujjain");
		userDTO.setPhoneNumber("963258911");
		userDTO.setPassword("chaudhary123");
		userDTO.setPincode(452020);
		
		try {
			int count  = userService.save(userDTO);
			
			if(count>0) {
				System.out.println("user save");
			}
			else {
				System.out.println("user not save");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void update() {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(1);
		userDTO.setName("Atul Patel");
		userDTO.setEmail("atul@gamil.com");
		userDTO.setAddress("Tejaji Nagar");
		userDTO.setCity("Indore");
		userDTO.setPhoneNumber("6264813736");
		userDTO.setPassword("Atul123");
		userDTO.setPincode(452020);
		
		try {
			int count  = userService.update(userDTO);
			
			if(count>0) {
				System.out.println("user update");
			}
			else {
				System.out.println("user not update");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void login() {
		String username = "Krishna@gamil.com";
		String password = "Krishna123";
		try {
			UserDTO userDTO = userService.login(username, password);
			
			if(userDTO != null) {
				System.out.println("login Succes");
				System.out.println(userDTO.getName() + " " + userDTO.getEmail() + " " + userDTO.getAddress() + " " + userDTO.getPincode() + " " + userDTO.getCity() + " " + userDTO.getPhoneNumber() + " " + userDTO.getCreatedAt());
			}
			else {
				System.out.println("login failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void findById() {
		int id = 1;
		try {
			UserDTO userDTO = userService.findById(id);
			
			if(userDTO != null) {
				System.out.println("user found");
				System.out.println(userDTO.getName() + " " + userDTO.getEmail() + " " + userDTO.getAddress() + " " + userDTO.getPincode() + " " + userDTO.getCity() + " " + userDTO.getPhoneNumber() + " " + userDTO.getCreatedAt());
			}
			else {
				System.out.println("user not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void findAll() {
		
		try {
			List<UserDTO> userDTOs = userService.findAll();
			
			if(userDTOs != null) {
				System.out.println("user found");
				for (UserDTO userDTO : userDTOs) {
					System.out.println(userDTO.getName() + " " + userDTO.getEmail() + " " + userDTO.getAddress() + " " + userDTO.getPincode() + " " + userDTO.getCity() + " " + userDTO.getPhoneNumber() + " " + userDTO.getCreatedAt());
				}
			}
			else {
				System.out.println("user not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete() {
		int id = 1;
		try {
			int count = userService.delete(id);
			
			if(count>0) {
				System.out.println("Delete User Success");
			}
			else {
				System.out.println("Delete User failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void main(String[] args) {
		DBUtil dbUtil = new DBUtil();
		UserDAO userDAO = new UserDAO(dbUtil);
		UserService userService = new UserService(userDAO);
		UserServletMain mainServlet = new UserServletMain(userService);
		mainServlet.save();
//		mainServlet.login();
//		mainServlet.findById();
//		mainServlet.update();
//		mainServlet.delete();
//		mainServlet.findAll();

	}
}
