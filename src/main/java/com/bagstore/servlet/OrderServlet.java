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
import com.bagstore.dto.OrderItemDTO;
import com.bagstore.dto.ProductDTO;
import com.bagstore.dto.WishListDTO;
import com.bagstore.service.CartService;
import com.bagstore.service.OrderItemService;
import com.bagstore.service.OrderService;
import com.bagstore.service.ProductService;
import com.bagstore.util.DBUtil;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/order")
public class OrderServlet extends HttpServlet {
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
	
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("OrderServlet servlet : DoGet Methode");
		String task = request.getParameter("task");
		System.out.println("task : " + task);

		if (task.equalsIgnoreCase("findAll")) {
			findAll(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("OrderServlet servlet : DoPost Methode");
		String task = request.getParameter("task");
		System.out.println("task : " + task);

		if (task.equalsIgnoreCase("save")) {
			save(request, response);
		}
	}
	
	public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		try {
			List<CartDTO> cartDTOs = cartService.findAllCartByUserId(userId);

			if (cartDTOs != null) {
				System.out.println("product In Cart found ");
				request.setAttribute("cartDTOs", cartDTOs);
				RequestDispatcher rd = request.getRequestDispatcher("order.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			System.out.println("Cart product Not  found");
			request.setAttribute("status", "error");
			request.setAttribute("message", "Cart product not found due to : " + e.getMessage());
			request.setAttribute("linkName", "Home");
			request.setAttribute("redirectUrl", "MainHome.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			rd.forward(request, response);
			e.printStackTrace();
		}

	}
	public void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		double totalAmount = Double.parseDouble(request.getParameter("totalAmount"));
		
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setUserId(userId);
		orderDTO.setTotalPrice(totalAmount);
		orderDTO.setAddress(request.getParameter("address"));
		orderDTO.setCity(request.getParameter("city"));
		orderDTO.setPincode(Integer.parseInt(request.getParameter("pinCode")));
		orderDTO.setPaymentMode(request.getParameter("paymentMode"));
		try {
			int orderId = orderService.save(orderDTO);
			List<CartDTO> cartDTOs = cartService.findAllCartByUserId(userId);
			for (CartDTO cartDTO : cartDTOs) {
				int productId = cartDTO.getProductId();
				int quntity = cartDTO.getQuntity();
				ProductDTO productDTO = productService.findProductByID(productId);
				double price = productDTO.getPrice();
				double totalPrice = quntity*price;
				
				OrderItemDTO orderItemDTO = new OrderItemDTO();
				orderItemDTO.setOrdersId(orderId);
				orderItemDTO.setProductId(productId);
				orderItemDTO.setQuantity(quntity);
				orderItemDTO.setTotalPrice(totalPrice);
				
				int count = orderItemService.saveOrderItem(orderItemDTO);
				cartService.deleteToCart(cartDTO.getId());
			}
			
			if (orderId>0) {
				System.out.println("Order Placed Succes Fully");
				request.setAttribute("status", "Success");
				request.setAttribute("message", "Order Placed Succes Full");
				request.setAttribute("linkName", "Home");
				request.setAttribute("redirectUrl", "MainHome.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			System.out.println("Failed to Place Order");
			request.setAttribute("status", "error");
			request.setAttribute("message", "Failed to Place Order due to : " + e.getMessage());
			request.setAttribute("linkName", "Home");
			request.setAttribute("redirectUrl", "MainHome.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			rd.forward(request, response);
			e.printStackTrace();
		}

	}
	

}
