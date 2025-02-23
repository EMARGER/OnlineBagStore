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
import com.bagstore.dao.ProductDAO;
import com.bagstore.dao.WishListDAO;
import com.bagstore.dto.CartDTO;
import com.bagstore.dto.ProductDTO;
import com.bagstore.dto.WishListDTO;
import com.bagstore.service.CartService;
import com.bagstore.service.ProductService;
import com.bagstore.service.WishListService;
import com.bagstore.util.DBUtil;

/**
 * Servlet implementation class WishlistServlet
 */
@WebServlet("/wishList")
public class WishlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DBUtil dbUtil = new DBUtil();

	WishListDAO wishListDAO = new WishListDAO(dbUtil);
	WishListService wishListService = new WishListService(wishListDAO);

	ProductDAO productDAO = new ProductDAO(dbUtil);
	ProductService productService = new ProductService(productDAO);

	CartDAO cartDAO = new CartDAO(dbUtil);
	CartService cartService = new CartService(cartDAO);

	public WishlistServlet() {

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Wishlist servlet : DoGet Methode");
		String task = request.getParameter("task");
		System.out.println("task : " + task);

		if (task.equalsIgnoreCase("findAll")) {
			findAll(request, response);
		}
		else if (task.equalsIgnoreCase("remove")) {
			removeFromWishlist(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Wishlist servlet : DoPost Methode");
		String task = request.getParameter("task");
		System.out.println("task : " + task);

		if (task.equalsIgnoreCase("addToCart")) {
			addToCart(request, response);
		}

	}

	public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		try {
			List<WishListDTO> wishListDTOs = wishListService.findAllWishListByUserId(userId);

			if (wishListDTOs != null) {
				System.out.println("wishlist product found ");
				request.setAttribute("wishListDTOs", wishListDTOs);
				RequestDispatcher rd = request.getRequestDispatcher("wishlist.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			System.out.println("wishlist product found");
			request.setAttribute("status", "error");
			request.setAttribute("message", "wishlist product not found  number due to : " + e.getMessage());
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
				request.setAttribute("linkName", "WishList");
				request.setAttribute("redirectUrl", "wishlist.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				rd.forward(request, response);
			} else {
//				System.out.println("Insert Data Failed");
				request.setAttribute("status", "failed");
				request.setAttribute("message", "Failed to Add to Cart Product");
				request.setAttribute("linkName", "WishList");
				request.setAttribute("redirectUrl", "wishlist.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				rd.forward(request, response);
			}

		} catch (Exception e) {
//			response.sendRedirect("SignUp.jsp");
			request.setAttribute("status", "error");
			request.setAttribute("message",
					"Failed to Add to Cart Product: " + e.getMessage() + "please try againe after some time");
			request.setAttribute("linkName", "WishList");
			request.setAttribute("redirectUrl", "wishlist.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			rd.forward(request, response);
			e.printStackTrace();
		}

	}

	public void removeFromWishlist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer wishlistId = Integer.parseInt(request.getParameter("wishlistId"));
		try {
			int count = wishListService.deleteToWishList(wishlistId);
			if (count > 0) {
				System.out.println(" Product SuccessFully Remove from Wishlist");
//				response.sendRedirect("Login.jsp");
				request.setAttribute("status", "Success");
				request.setAttribute("message", "Product SuccessFully Remove from Wishlist");
				request.setAttribute("linkName", "WishList");
				request.setAttribute("redirectUrl", "wishlist.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				rd.forward(request, response);
			} else {
//				System.out.println("Insert Data Failed");
				request.setAttribute("status", "failed");
				request.setAttribute("message", "Failed to Remove Product from Wishlist");
				request.setAttribute("linkName", "WishList");
				request.setAttribute("redirectUrl", "wishlist.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				rd.forward(request, response);
			}
		}  catch (Exception e) {
//			response.sendRedirect("SignUp.jsp");
			request.setAttribute("status", "error");
			request.setAttribute("message",
					"Failed to Remove Product from Wishlist " + e.getMessage() + "please try againe after some time");
			request.setAttribute("linkName", "WishList");
			request.setAttribute("redirectUrl", "wishlist.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			rd.forward(request, response);
			e.printStackTrace();
		}
	}

}
