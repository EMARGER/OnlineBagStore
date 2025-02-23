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
import com.bagstore.dao.OrderDAO;
import com.bagstore.dao.OrderItemDAO;
import com.bagstore.dao.ProductDAO;
import com.bagstore.dto.CartDTO;
import com.bagstore.dto.OrderDTO;
import com.bagstore.service.CartService;
import com.bagstore.service.OrderItemService;
import com.bagstore.service.OrderService;
import com.bagstore.service.ProductService;
import com.bagstore.util.DBUtil;

/**
 * Servlet implementation class historyServlet
 */
@WebServlet("/history")
public class HistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	DBUtil dbUtil = new DBUtil();

	ProductDAO productDAO = new ProductDAO(dbUtil);
	ProductService productService = new ProductService(productDAO);

	CartDAO cartDAO = new CartDAO(dbUtil);
	CartService cartService = new CartService(cartDAO);
	
	OrderDAO orderDAO = new OrderDAO(dbUtil);
	OrderService orderService = new OrderService(orderDAO);
	
	OrderItemDAO orderItemDAO= new OrderItemDAO(dbUtil);
	OrderItemService orderItemService= new OrderItemService(orderItemDAO);
    
    public HistoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("HistoryServlet servlet : DoGet Methode");
		String task = request.getParameter("task");
		System.out.println("task : " + task);

		if (task.equalsIgnoreCase("findAll")) {
			findAll(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		try {
			
			List<OrderDTO> orderDTOs = orderService.findAllOrdersByUserId(userId);
			
			if (orderDTOs != null) {
				System.out.println("User Orders History found ");
				request.setAttribute("orderDTOs", orderDTOs);
				RequestDispatcher rd = request.getRequestDispatcher("history.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			System.out.println("User Orders History not found");
			request.setAttribute("status", "error");
			request.setAttribute("message", "User Orders History not found : " + e.getMessage());
			request.setAttribute("linkName", "Home");
			request.setAttribute("redirectUrl", "MainHome.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			rd.forward(request, response);
			e.printStackTrace();
		}

	}

}
