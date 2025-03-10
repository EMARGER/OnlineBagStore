package com.bagstore.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.bagstore.dao.CartDAO;
import com.bagstore.dao.CategoryDAO;
import com.bagstore.dao.ProductDAO;
import com.bagstore.dao.UserDAO;
import com.bagstore.dao.WishListDAO;
import com.bagstore.dto.CartDTO;
import com.bagstore.dto.CategoryDTO;
import com.bagstore.dto.ProductDTO;
import com.bagstore.dto.UserDTO;
import com.bagstore.dto.WishListDTO;
import com.bagstore.service.CartService;
import com.bagstore.service.CategoryService;
import com.bagstore.service.ProductService;
import com.bagstore.service.UserService;
import com.bagstore.service.WishListService;
import com.bagstore.util.DBUtil;

/**
 * Servlet implementation class MainHomeServlet
 */
@WebServlet("/mainHome")
public class MainHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DBUtil dbUtil = new DBUtil();
	
	UserDAO userDAO = new UserDAO(dbUtil);
	UserService userService = new UserService(userDAO);
	
	CategoryDAO categoryDAO = new CategoryDAO(dbUtil);
	CategoryService categoryService = new CategoryService(categoryDAO);

	ProductDAO productDAO = new ProductDAO(dbUtil);
	ProductService productService = new ProductService(productDAO);

	WishListDAO wishListDAO = new WishListDAO(dbUtil);
	WishListService wishListService = new WishListService(wishListDAO);

	CartDAO cartDAO = new CartDAO(dbUtil);
	CartService cartService = new CartService(cartDAO);

	public MainHomeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Main Home Servlet : DoGet Method");
		String task = request.getParameter("task");
		System.out.println("TASK : " + task);
		if (task.equalsIgnoreCase("findAll")) {

			try {

				findAllProduct(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (task.equalsIgnoreCase("logout")) {
			logout(request, response);
		} else if (task.equalsIgnoreCase("findProductsByCategory")) {
			try {
				findProductByCategoryId(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (task.equalsIgnoreCase("findProductByDefault")) {
			try {
				findProductByDefault(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("CategoryServlet : DoPost Method");
		String task = request.getParameter("task");
		System.out.println("TASK : " + task);
		if (task.equalsIgnoreCase("saveToWishlist")) {
			saveToWishlist(request, response);
		} else if (task.equalsIgnoreCase("addToCart")) {
			addToCart(request, response);
		}
		else if (task.equalsIgnoreCase("updateDataById")) {
			try {
				updateDataById(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		

	}

	public void findAllProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String categoryName = request.getParameter("category");
		System.out.println(categoryName);
		CategoryDTO categoryDTO = categoryService.findByName(categoryName);

		int categoryId = categoryDTO.getId();

		try {
			List<ProductDTO> productDTOs = productService.findProductByCategoryId(categoryId);

			if (productDTOs != null) {
				System.out.println("category found for main ");
				request.setAttribute("productDTOs", productDTOs);
				RequestDispatcher rd = request.getRequestDispatcher("MainHome.jsp");
				rd.forward(request, response);
				System.out.println("123");

			} else {
				System.out.println("category not found");
				request.setAttribute("status", "Not Found");
				request.setAttribute("message", "No category Found for " + categoryName + " number");
				request.setAttribute("linkName", "Home");
				request.setAttribute("redirectUrl", "MainHome.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				rd.forward(request, response);

			}
		} catch (Exception e) {
			System.out.println("category not found");
			request.setAttribute("status", "error");
			request.setAttribute("message",
					"No category Found for " + categoryName + " number due to : " + e.getMessage());
			request.setAttribute("linkName", "Home");
			request.setAttribute("redirectUrl", "MainHome.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			rd.forward(request, response);
			e.printStackTrace();
		}

	}

	public void findProductByCategoryId(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Integer productId = Integer.parseInt(request.getParameter("id"));

		System.out.println(productId);
		ProductDTO productDTO = productService.findProductByID(productId);

		int categoryId = productDTO.getCategoryId();

		try {
			List<ProductDTO> productDTOs = productService.findProductByCategoryId(categoryId);

			if (productDTOs != null) {
				System.out.println("category found for main ");
				request.setAttribute("productDTOs", productDTOs);
				RequestDispatcher rd = request.getRequestDispatcher("MainHome.jsp");
				rd.forward(request, response);
				System.out.println("123");

			} else {
				System.out.println("category not found");
				request.setAttribute("status", "Not Found");
				request.setAttribute("message", "No category Found for  number");
				request.setAttribute("linkName", "Home");
				request.setAttribute("redirectUrl", "MainHome.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				rd.forward(request, response);

			}
		} catch (Exception e) {
			System.out.println("category not found");
			request.setAttribute("status", "error");
			request.setAttribute("message", "No category Found for  number due to : " + e.getMessage());
			request.setAttribute("linkName", "Home");
			request.setAttribute("redirectUrl", "MainHome.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			rd.forward(request, response);
			e.printStackTrace();
		}

	}

	public void findProductByDefault(HttpServletRequest request, HttpServletResponse response) throws Exception {

		

		int categoryId = 9;

		try {
			List<ProductDTO> productDTOs = productService.findProductByCategoryId(9);

			if (productDTOs != null) {
				System.out.println("category found for main ");
				request.setAttribute("productDTOs", productDTOs);
				RequestDispatcher rd = request.getRequestDispatcher("MainHome.jsp");
				rd.forward(request, response);
				System.out.println("123");

			} else {
				System.out.println("category not found");
				request.setAttribute("status", "Not Found");
				request.setAttribute("message", "No category Found for  number");
				request.setAttribute("linkName", "Home");
				request.setAttribute("redirectUrl", "MainHome.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				rd.forward(request, response);

			}
		} catch (Exception e) {
			System.out.println("category not found");
			request.setAttribute("status", "error");
			request.setAttribute("message", "No category Found for  number due to : " + e.getMessage());
			request.setAttribute("linkName", "Home");
			request.setAttribute("redirectUrl", "MainHome.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			rd.forward(request, response);
			e.printStackTrace();
		}

	}

	public void addToCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer userId = Integer.parseInt(request.getParameter("userId"));

		Integer productId = Integer.parseInt(request.getParameter("productId"));

		try {
			CartDTO cartDTO = cartService.findCartByUserIdProductId(userId, productId);
			ProductDTO productDTO = productService.findProductByID(productId);

			Integer quantity = 0;
			int count = 0;
			if (cartDTO == null) {
				quantity = 1;
				CartDTO cartDTO1 = new CartDTO();
				cartDTO1.setProductId(productId);
				cartDTO1.setUserId(userId);
				cartDTO1.setQuntity(quantity);

				count = cartService.saveToCart(cartDTO1);
			} else {
				quantity = cartDTO.getQuntity() + 1;
				CartDTO cartDTO1 = new CartDTO();
				cartDTO1.setId(cartDTO.getId());
				cartDTO1.setQuntity(quantity);
				count = cartService.updateCart(cartDTO1);
			}

			if (count > 0) {
				System.out.println(" Product Add to cart  Succesfully");
//				response.sendRedirect("Login.jsp");
				request.setAttribute("status", "Success");
				request.setAttribute("message", "Add To Cart Product Succesfully.");
				request.setAttribute("linkName", "MainHome");
				request.setAttribute("servalet", "mainHome");
				request.setAttribute("task", "findProductsByCategory");
				request.setAttribute("id", productId);
				RequestDispatcher rd = request.getRequestDispatcher("message2.jsp");
				rd.forward(request, response);
			} else {
//				System.out.println("Insert Data Failed");
				request.setAttribute("status", "failed");
				request.setAttribute("message", "Failed to Add to Cart Product");
				request.setAttribute("linkName", "MainHome");
				request.setAttribute("servalet", "mainHome");
				request.setAttribute("task", "findProductsByCategory");
				request.setAttribute("id", productId);
				RequestDispatcher rd = request.getRequestDispatcher("message2.jsp");
				rd.forward(request, response);
			}

		} catch (Exception e) {
//			response.sendRedirect("SignUp.jsp");
			request.setAttribute("status", "error");
			request.setAttribute("message",
					"Failed to Add to Cart Product: " + e.getMessage() + "please try againe after some time");
			request.setAttribute("linkName", "MainHome");
			request.setAttribute("servalet", "mainHome");
			request.setAttribute("task", "findProductsByCategory");
			request.setAttribute("id", productId);
			RequestDispatcher rd = request.getRequestDispatcher("message2.jsp");
			rd.forward(request, response);
			e.printStackTrace();
		}

	}

	public void saveToWishlist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer userId = Integer.parseInt(request.getParameter("userId"));

		Integer productId = Integer.parseInt(request.getParameter("productId"));

		WishListDTO wishListDTO;
		try {
			wishListDTO = wishListService.findWishlistByUserIdProductId(userId, productId);
			if (wishListDTO == null) {
				WishListDTO wishListDTO1 = new WishListDTO();
				wishListDTO1.setProductId(productId);
				wishListDTO1.setUserId(userId);

				int count = wishListService.saveToWishList(wishListDTO1);

				if (count > 0) {
					System.out.println(" Product Save to Wishlist  Succesfully");
//					response.sendRedirect("Login.jsp");
					request.setAttribute("status", "Success");
					request.setAttribute("message", "Product Save to Wishlist  Succesfully.");
					request.setAttribute("linkName", "MainHome");
					request.setAttribute("servalet", "mainHome");
					request.setAttribute("task", "findProductsByCategory");
					request.setAttribute("id", productId);
					RequestDispatcher rd = request.getRequestDispatcher("message2.jsp");
					rd.forward(request, response);
				}
			} else {
//				System.out.println("Insert Data Failed");
				request.setAttribute("status", "Notice");
				request.setAttribute("message", "That Product Already Exist In your WishList");
				request.setAttribute("linkName", "MainHome");
				request.setAttribute("servalet", "mainHome");
				request.setAttribute("task", "findProductsByCategory");
				request.setAttribute("id", productId);
				RequestDispatcher rd = request.getRequestDispatcher("message2.jsp");
				rd.forward(request, response);
			}

		} catch (Exception e) {
//			response.sendRedirect("SignUp.jsp");
			request.setAttribute("status", "error");
			request.setAttribute("message",
					"Failed to Save Product in Wishlist: " + e.getMessage() + "please try againe after some time");
			request.setAttribute("linkName", "MainHome");
			request.setAttribute("servalet", "mainHome");
			request.setAttribute("task", "findProductsByCategory");
			request.setAttribute("id", productId);
			RequestDispatcher rd = request.getRequestDispatcher("message2.jsp");
			rd.forward(request, response);
			e.printStackTrace();
		}

	}

	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("FirstHome.jsp");
		requestDispatcher.forward(request, response);
	}
	
	public void updateDataById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 UserDTO userDTO = new UserDTO();
		 
		 	Integer id = Integer.parseInt(request.getParameter("userId"));
		 	userDTO.setId(id);
			userDTO.setName(request.getParameter("fullName"));
			userDTO.setEmail(request.getParameter("email"));
			userDTO.setAddress(request.getParameter("address"));
			userDTO.setCity(request.getParameter("city"));
			userDTO.setPhoneNumber(request.getParameter("phoneNumber"));
			userDTO.setPassword(request.getParameter("password"));
			userDTO.setPincode(Integer.parseInt(request.getParameter("pincode")));
			try {
				int count = userService.update(userDTO);
				
				if(count>0) {
					System.out.println("Update Data Succesfully");
					
					request.setAttribute("status","Success");
					request.setAttribute("message","User Account Updated Sucessfully. To see changes please re-login");
					request.setAttribute("linkName", "Profile");
					request.setAttribute("redirectUrl","profile.jsp");
					RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
					rd.forward(request, response);
				}
				else {
					System.out.println("Update Data Failed");
					
					request.setAttribute("status","failed");
					request.setAttribute("message","Failed to Update user account. Please try again to Update");
					request.setAttribute("linkName", "Edit");
					request.setAttribute("redirectUrl","Edit.jsp");
					RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
					rd.forward(request, response);
				}
			} catch (ClassNotFoundException | SQLException e) {
				request.setAttribute("status","error");
				request.setAttribute("message","Failed to Upadate user account due to: "+e.getMessage()+"please try againe after some time");
				request.setAttribute("linkName", "Edit");
				request.setAttribute("redirectUrl","Edit.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				rd.forward(request, response);
				e.printStackTrace();
			}
	 }

}
