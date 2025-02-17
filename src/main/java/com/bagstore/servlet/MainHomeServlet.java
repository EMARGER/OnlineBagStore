package com.bagstore.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.bagstore.dao.CartDAO;
import com.bagstore.dao.CategoryDAO;
import com.bagstore.dao.ProductDAO;
import com.bagstore.dao.WishListDAO;
import com.bagstore.dto.CartDTO;
import com.bagstore.dto.CategoryDTO;
import com.bagstore.dto.ProductDTO;
import com.bagstore.dto.UserDTO;
import com.bagstore.dto.WishListDTO;
import com.bagstore.service.CartService;
import com.bagstore.service.CategoryService;
import com.bagstore.service.ProductService;
import com.bagstore.service.WishListService;
import com.bagstore.util.DBUtil;

/**
 * Servlet implementation class MainHomeServlet
 */
@WebServlet("/mainHome")
public class MainHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	DBUtil dbUtil = new DBUtil();
	CategoryDAO categoryDAO = new CategoryDAO(dbUtil);
	CategoryService categoryService = new CategoryService(categoryDAO);
	
	ProductDAO productDAO=new ProductDAO(dbUtil);
	ProductService productService=new ProductService(productDAO);
	
	WishListDAO wishListDAO= new WishListDAO(dbUtil);
	WishListService wishListService= new WishListService(wishListDAO);
	
	CartDAO cartDAO = new CartDAO(dbUtil);
	CartService cartService = new CartService(cartDAO);
	
    public MainHomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Main Home Servlet : DoGet Method");
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("CategoryServlet : DoPost Method");
		String task = request.getParameter("task");
		System.out.println("TASK : " + task);
		 if(task.equalsIgnoreCase("saveToWishlist")) {			 
			 saveToWishlist(request, response);
		}
		 else if(task.equalsIgnoreCase("addToCart")) {
			 addToCart(request, response);
			}
		
		
	}
	
	

	public void findAllProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String categoryName= request.getParameter("category");
		System.out.println(categoryName);
		CategoryDTO categoryDTO = categoryService.findByName(categoryName);
		
		int categoryId = categoryDTO.getId();
		
		try {
			List<ProductDTO> productDTOs = productService.findProductByCategoryId(categoryId);
			
			if(productDTOs != null) {
				System.out.println("category found for main ");
				request.setAttribute("productDTOs", productDTOs);
				RequestDispatcher rd = request.getRequestDispatcher("MainHome.jsp");
				rd.forward(request, response);
				System.out.println("123");
				
			}
			else {
				System.out.println("category not found");
				request.setAttribute("status","Not Found");
				request.setAttribute("message","No category Found for "+categoryName+" number");
				request.setAttribute("linkName","Home");
				request.setAttribute("redirectUrl", "MainHome.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				rd.forward(request, response);
			
			}
		} catch (Exception e) {
			System.out.println("category not found");
			request.setAttribute("status","error");
			request.setAttribute("message","No category Found for "+categoryName+" number due to : "+  e.getMessage());
			request.setAttribute("linkName","Home");
			request.setAttribute("redirectUrl", "MainHome.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			rd.forward(request, response);
			e.printStackTrace();
		}
		
	}
	public void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		
		Integer productId = Integer.parseInt(request.getParameter("productId"));
		Integer quantity = 1;
		CartDTO cartDTO = new CartDTO();
		cartDTO.setProductId(productId);
		cartDTO.setUserId(userId);
		cartDTO.setQuntity(quantity);
		
		try {
			int count = cartService.saveToCart(cartDTO);
			if(count>0) {
				System.out.println(" Product Add to cart  Succesfully");
//				response.sendRedirect("Login.jsp");
				request.setAttribute("status","Success");
				request.setAttribute("message","Add To Cart Product Succesfully.");
				request.setAttribute("linkName", "MainHome");
				request.setAttribute("redirectUrl","MainHome.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				rd.forward(request, response);
			}
			else {
//				System.out.println("Insert Data Failed");
				request.setAttribute("status","failed");
				request.setAttribute("message","Failed to Add to Cart Product");
				request.setAttribute("linkName", "MainHome");
				request.setAttribute("redirectUrl","MainHome.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
//			response.sendRedirect("SignUp.jsp");
			request.setAttribute("status","error");
			request.setAttribute("message","Failed to Add to Cart Product: "+e.getMessage()+"please try againe after some time");
			request.setAttribute("linkName", "MainHome");
			request.setAttribute("redirectUrl","MainHome.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			rd.forward(request, response);
			e.printStackTrace();
		} 
	}
	
	

	public void saveToWishlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		
		Integer productId = Integer.parseInt(request.getParameter("productId"));
		WishListDTO wishListDTO = new WishListDTO();
		wishListDTO.setProductId(productId);
		wishListDTO.setUserId(userId);
		
		try {
			int count = wishListService.saveToWishList(wishListDTO);
			if(count>0) {
				System.out.println(" Product Save to Wishlist  Succesfully");
//				response.sendRedirect("Login.jsp");
				request.setAttribute("status","Success");
				request.setAttribute("message","Product Save to Wishlist  Succesfully.");
				request.setAttribute("linkName", "MainHome");
				request.setAttribute("redirectUrl","MainHome.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				rd.forward(request, response);
			}
			else {
//				System.out.println("Insert Data Failed");
				request.setAttribute("status","failed");
				request.setAttribute("message","Failed to Save Product in Wishlist");
				request.setAttribute("linkName", "MainHome");
				request.setAttribute("redirectUrl","MainHome.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
//			response.sendRedirect("SignUp.jsp");
			request.setAttribute("status","error");
			request.setAttribute("message","Failed to Save Product in Wishlist: "+e.getMessage()+"please try againe after some time");
			request.setAttribute("linkName", "MainHome");
			request.setAttribute("redirectUrl","MainHome.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			rd.forward(request, response);
			e.printStackTrace();
		} 
	}
	


}
