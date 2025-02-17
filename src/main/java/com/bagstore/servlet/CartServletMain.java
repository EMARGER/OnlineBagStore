package com.bagstore.servlet;

import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

import com.bagstore.dao.CartDAO;
import com.bagstore.dto.CartDTO;
import com.bagstore.service.CartService;
import com.bagstore.util.DBUtil;

public class CartServletMain {

	private CartService cartService;
	public CartServletMain(CartService cartService) {
		this.cartService=cartService;
	}
	
	public void saveCart() {
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
	
	public void updateCart() {
		CartDTO cartDTO=new CartDTO();
		cartDTO.setUserId(1);
		cartDTO.setProductId(1);
		cartDTO.setQuntity(2);
		
		cartDTO.setId(2);
		
		try {
			int count=cartService.updateCart(cartDTO);
			if(count >0) {
				System.out.println("Cart update successfully");
			}else {
				System.out.println("Cart not update");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	
	public void deleteCartById() {
		Integer id=4;
		
		
		try {
			int count =cartService.deleteToCart(id);
			if(count >0) {
				System.out.println("Cart deleted successfully");
				
			}else {
				System.out.println("Cart delete to failed");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public void cartFindById() {
		Integer id=5;
		
		
		try {
			CartDTO cartDTO =cartService.findCartByID(id);
			if(cartDTO !=null) {
				System.out.println("Cart Id "+cartDTO.getId());
				System.out.println("Cart product id "+cartDTO.getProductId());
				System.out.println("Cart Quantity: "+cartDTO.getQuntity());
				System.out.println("Cart userId: "+cartDTO.getUserId());
				System.out.println("Cart Created Date: "+cartDTO.getAddedAt());
				
			}else {
				System.out.println("Cart not find");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public void findAllCart() {
		
		
		
		try {
			List<CartDTO> cartDTOs =cartService.findAllCart();
			if(cartDTOs !=null) {
			
			for (CartDTO cartDTO : cartDTOs) {
				System.out.println("Cart Id "+cartDTO.getId());
				System.out.println("Cart product id "+cartDTO.getProductId());
				System.out.println("Cart Quantity: "+cartDTO.getQuntity());
				System.out.println("Cart userId: "+cartDTO.getUserId());
				System.out.println("Cart Created Date: "+cartDTO.getAddedAt());
				System.out.println("================");
				
			}
			}else {
				System.out.println("Cart not find");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
	DBUtil dbUtil= new DBUtil();
	CartDAO cartDAO = new CartDAO(dbUtil);
	CartService cartService= new CartService(cartDAO);
	CartServletMain cartMainServlet = new CartServletMain(cartService);
	//cartMainServlet.saveCart();
	//cartMainServlet.deleteCartById();
	//cartMainServlet.cartFindById();
	cartMainServlet.findAllCart();
	}
	
}
