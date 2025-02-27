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
import com.bagstore.dto.WishListDTO;
import com.bagstore.service.CartService;
import com.bagstore.service.ProductService;
import com.bagstore.service.WishListService;
import com.bagstore.util.DBUtil;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	DBUtil dbUtil = new DBUtil();

	ProductDAO productDAO = new ProductDAO(dbUtil);
	ProductService productService = new ProductService(productDAO);

	CartDAO cartDAO = new CartDAO(dbUtil);
	CartService cartService = new CartService(cartDAO);
    
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("CartServlet servlet : DoGet Methode");
		String task = request.getParameter("task");
		System.out.println("task : " + task);

		if (task.equalsIgnoreCase("findAll")) {
			findAll(request, response);
		}
		else if (task.equalsIgnoreCase("remove")) {
			removeFromCart(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("id"));
		try {
			List<CartDTO> cartDTOs = cartService.findAllCartByUserId(userId);

			if (cartDTOs != null) {
				System.out.println("wishlist product found ");
				request.setAttribute("cartDTOs", cartDTOs);
				RequestDispatcher rd = request.getRequestDispatcher("cart.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			System.out.println("Cart product found");
			request.setAttribute("status", "error");
			request.setAttribute("message", "Cart product not found  number due to : " + e.getMessage());
			request.setAttribute("linkName", "Home");
			request.setAttribute("redirectUrl", "MainHome.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			rd.forward(request, response);
			e.printStackTrace();
		}

	}
	
	public void removeFromCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer cardId = Integer.parseInt(request.getParameter("cartId"));
		int userId = Integer.parseInt(request.getParameter("userId"));
		try {
			int count = cartService.deleteToCart(cardId);
			if (count > 0) {
				System.out.println(" Product SuccessFully Remove from Cart");
//				response.sendRedirect("Login.jsp");
				request.setAttribute("status", "Success");
				request.setAttribute("message", "Product SuccessFully Remove from Cart");
				request.setAttribute("linkName", "Cart");
				request.setAttribute("servalet", "cart");
				request.setAttribute("task", "findAll");
				request.setAttribute("id", userId);
				RequestDispatcher rd = request.getRequestDispatcher("message2.jsp");
				rd.forward(request, response);
			} else {
//				System.out.println("Insert Data Failed");
				request.setAttribute("status", "failed");
				request.setAttribute("message", "Failed to Remove Product from Cart");
				request.setAttribute("linkName", "Cart");
				request.setAttribute("servalet", "cart");
				request.setAttribute("task", "findAll");
				request.setAttribute("id", userId);
				RequestDispatcher rd = request.getRequestDispatcher("message2.jsp");
				rd.forward(request, response);
			}
		}  catch (Exception e) {
//			response.sendRedirect("SignUp.jsp");
			request.setAttribute("status", "error");
			request.setAttribute("message",
					"Failed to Remove Product from Cart " + e.getMessage() + "please try againe after some time");
			request.setAttribute("linkName", "Cart");
			request.setAttribute("servalet", "cart");
			request.setAttribute("task", "findAll");
			request.setAttribute("id", userId);
			RequestDispatcher rd = request.getRequestDispatcher("message2.jsp");
			rd.forward(request, response);
			e.printStackTrace();
		}
	}

}
