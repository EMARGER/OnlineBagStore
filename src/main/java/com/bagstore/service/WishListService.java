package com.bagstore.service;

import java.util.List;

import com.bagstore.dao.WishListDAO;
import com.bagstore.dto.WishListDTO;

public class WishListService {

	private WishListDAO wishListDAO;
	public  WishListService(WishListDAO wishListDAO) {
		this.wishListDAO=wishListDAO;
		
	}
	public int saveToWishList(WishListDTO wishListDTO) throws Exception {
		int count= wishListDAO.saveToWishlist(wishListDTO);
		return count;
	}
	
	public int deleteToWishList(Integer id) throws Exception {
		int count= wishListDAO.deleteToWishlist(id);
		return count;
	}
	public WishListDTO findToWishListByID(Integer id) throws Exception {
		return wishListDAO.findWishListById(id);
	
	}
	public List<WishListDTO> findAllWishList() throws Exception {
		return wishListDAO.findAllWishList();
	
	}
	
}
