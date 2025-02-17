package com.bagstore.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.bagstore.dao.CartDAO;
import com.bagstore.dto.CartDTO;
import com.bagstore.service.CartService;
import com.bagstore.util.DBUtil;


@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DBUtil dbUtil= new DBUtil();
	CartDAO cartDAO = new CartDAO(dbUtil);
	CartService cartService= new CartService(cartDAO);
   
    public CartServlet() {
        super();
        }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String task = request.getParameter("task");
		System.out.println("TASK : " + task);
		 if(task.equalsIgnoreCase("addToCart")) {
			saveCart(request, response);
			
		}
		doGet(request, response);
	}
	
	public void saveCart(HttpServletRequest request, HttpServletResponse response) {
		request.getParameter(getServletInfo())
		
		CartDTO cartDTO=new CartDTO();
		cartDTO.setUserId(11);
		cartDTO.setProductId(1);
		cartDTO.setQuntity(5);
		
		try {
			int count=cartService.saveToCart(cartDTO);
			if(count >0) {
				System.out.println("peoduct add to cart successfully");
			}else {
				System.out.println("product not save to cart");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
