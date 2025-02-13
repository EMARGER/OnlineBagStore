package com.bagstore.service;

import java.sql.SQLException;
import java.util.List;

import com.bagstore.dao.CartDAO;
import com.bagstore.dto.CartDTO;

public class CartService {
private CartDAO cartDAO;
	
	public CartService(CartDAO cartDAO) {
		this.cartDAO=cartDAO;
	
	}
	
	public int saveToCart(CartDTO cartDTO) throws SQLException {
		int count=cartDAO.addToCart(cartDTO);
		return count;
	}
	public int updateCart(CartDTO cartDTO) throws SQLException {
		int count=cartDAO.updateCart(cartDTO);
		return count;
	}
	public int deleteToCart(Integer id ) throws SQLException {
		int count=cartDAO.deleteToCart(id);
		return count;
	}
	public List<CartDTO> findAllCart() throws Exception {
		return cartDAO.findAllCart();
	}
	public CartDTO findCartByID(Integer id) throws Exception {
		return cartDAO.findCartById(id);
	}
}
