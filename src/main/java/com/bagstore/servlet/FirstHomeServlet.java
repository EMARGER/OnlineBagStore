package com.bagstore.servlet;

import jakarta.servlet.RequestDispatcher;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.bagstore.dao.CategoryDAO;
import com.bagstore.dao.ProductDAO;
import com.bagstore.dao.UserDAO;
import com.bagstore.dto.CategoryDTO;
import com.bagstore.dto.ProductDTO;
import com.bagstore.dto.UserDTO;
import com.bagstore.service.CategoryService;
import com.bagstore.service.ProductService;
import com.bagstore.service.UserService;
import com.bagstore.util.DBUtil;

@WebServlet("/firstHome")
public class FirstHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DBUtil dbUtil = new DBUtil();
	CategoryDAO categoryDAO = new CategoryDAO(dbUtil);
	CategoryService categoryService = new CategoryService(categoryDAO);
	
	ProductDAO productDAO=new ProductDAO(dbUtil);
	ProductService productService=new ProductService(productDAO);
	
	UserDAO userDAO = new UserDAO(dbUtil);
	UserService userService = new UserService(userDAO);
	
    public FirstHomeServlet() {
        System.out.println("CategoryServlet : Object Created");
    }

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null; 
	}

//	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("CategoryServlet : DoGet Method");
		String task = request.getParameter("task");
		System.out.println("TASK : " + task);
		if(task.equalsIgnoreCase("findAll")) {
			
			try {
				
				findAllProduct(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("CategoryServlet : DoPost Method");
		String task = request.getParameter("task");
		System.out.println("TASK : " + task);
		
		 if(task.equalsIgnoreCase("login")) {
			login(request, response);
		}
		 else if(task.equalsIgnoreCase("signup")) {
				save(request, response);
			}
		
		
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doHead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}


	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doTrace(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	public void findAllProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String categoryName= request.getParameter("category");
		System.out.println(categoryName);
		CategoryDTO categoryDTO = categoryService.findByName(categoryName);
		
		int categoryId = categoryDTO.getId();
		
		try {
			List<ProductDTO> productDTOs = productService.findProductByCategoryId(categoryId);
			System.out.println(productDTOs);
			if(productDTOs != null) {
				System.out.println("category found ");
				request.setAttribute("productDTOs", productDTOs);
				RequestDispatcher rd = request.getRequestDispatcher("FirstHome.jsp");
				rd.forward(request, response);
				
			}
			else {
				System.out.println("category not found");
				request.setAttribute("status","Not Found");
				request.setAttribute("message","No category Found for "+categoryName+" number");
				request.setAttribute("linkName","Home");
				request.setAttribute("redirectUrl", "FirstHome.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				rd.forward(request, response);
			
			}
		} catch (Exception e) {
			System.out.println("category not found");
			request.setAttribute("status","error");
			request.setAttribute("message","No category Found for "+categoryName+" number due to : "+  e.getMessage());
			request.setAttribute("linkName","Home");
			request.setAttribute("redirectUrl", "FirstHome.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			rd.forward(request, response);
			e.printStackTrace();
		}
		
	}
	
	public void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDTO userDTO = new UserDTO();
		userDTO.setName(request.getParameter("fullName"));
		userDTO.setEmail(request.getParameter("email"));
		userDTO.setAddress(request.getParameter("address"));
		userDTO.setCity(request.getParameter("city"));
		userDTO.setPhoneNumber(request.getParameter("phoneNumber"));
		userDTO.setPassword(request.getParameter("password"));
		userDTO.setPincode(Integer.parseInt(request.getParameter("pincode")));
		userDTO.setImg("profileLogo.png");
		
		try {
			int count = userService.save(userDTO);
			if(count>0) {
				System.out.println("Insert Data Succesfully");
//				response.sendRedirect("Login.jsp");
				request.setAttribute("status","Success");
				request.setAttribute("message","User Account Crreated Sucessfully. Now you can login in our page");
				request.setAttribute("linkName", "Login");
				request.setAttribute("redirectUrl","login.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				rd.forward(request, response);
			}
			else {
//				System.out.println("Insert Data Failed");
				request.setAttribute("status","failed");
				request.setAttribute("message","Failed to created user account. Please try againe to register");
				request.setAttribute("linkName", "SignUp");
				request.setAttribute("redirectUrl","signUp.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
//			response.sendRedirect("SignUp.jsp");
			request.setAttribute("status","error");
			request.setAttribute("message","Failed to created user account due to: "+e.getMessage()+"please try againe after some time");
			request.setAttribute("linkName", "SignUp");
			request.setAttribute("redirectUrl","signUp.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			rd.forward(request, response);
			e.printStackTrace();
		} 
	}
	
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("userName");
		String password = request.getParameter("password");
		try {
			UserDTO userDTO = userService.login(username, password);
			if(userDTO!=null) {
				System.out.println("Login Succesfully");
				
				List<ProductDTO> productDTOs = productService.findProductByCategoryId(10);
				
				HttpSession session = request.getSession();
				session.setAttribute("userId", userDTO.getId());
				session.setAttribute("userName", userDTO.getName());
				session.setAttribute("userEmail", userDTO.getEmail());
				session.setAttribute("userPhoneNumber", userDTO.getPhoneNumber());
				session.setAttribute("userAddress", userDTO.getAddress());
				session.setAttribute("userCity", userDTO.getCity());
				session.setAttribute("userPincode", userDTO.getPincode());
				session.setAttribute("userImg", userDTO.getImg());
				session.setMaxInactiveInterval(500000);
				
				
				request.setAttribute("loginUserDTO", userDTO);
				
				request.setAttribute("productDTOs", productDTOs);
				
				RequestDispatcher rd = request.getRequestDispatcher("MainHome.jsp");
				rd.forward(request, response);
			}
			else {
				System.out.println("invalid user name or pass");
				request.setAttribute("status","error");
				request.setAttribute("message","Invalid username or Password. Please check username and password and retry again");
				request.setAttribute("linkName", "Login");
				request.setAttribute("redirectUrl","login.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("status","error");
			request.setAttribute("message","Failed to login due to: " + e.getMessage());
			request.setAttribute("linkName", "Login");
			request.setAttribute("redirectUrl","Login.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			rd.forward(request, response);
			e.printStackTrace();
		} 
	}

}
