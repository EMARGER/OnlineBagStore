package com.bagstore.servlet;

import java.util.List;

import com.bagstore.dao.WishListDAO;
import com.bagstore.dto.WishListDTO;
import com.bagstore.service.WishListService;
import com.bagstore.util.DBUtil;

public class WishListMainServlet {

	private WishListService wishListService;

	public WishListMainServlet(WishListService wishListService) {
		this.wishListService = wishListService;
	}

	public void savetToWishList() {
		WishListDTO wishListDTO = new WishListDTO();
		wishListDTO.setProductId(5);
		wishListDTO.setUserId(16);
		try {
			int count = wishListService.saveToWishList(wishListDTO);
			if (count > 0) {
				System.out.println("Product save to WishList Successfully");
			} else {
				System.out.println("Product save to WishList Failed");

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void deleteToWishList() {
		Integer id = 2;
		try {
			int count = wishListService.deleteToWishList(id);
			if (count > 0) {
				System.out.println("Product Delete to WishList Successfully");
			} else {
				System.out.println("Product Delete to WishList Failed");

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void findProductToWishListById() {
		Integer id = 2;
		try {
			WishListDTO wishListDTO = wishListService.findToWishListByID(id);
			if (wishListDTO != null) {
				System.out.println("WishList ID:" + wishListDTO.getId());
				System.out.println("Product id in WishList :" + wishListDTO.getProductId());
				System.out.println("User id in WishList :" + wishListDTO.getUserId());
				System.out.println("WishList Created Date:" + wishListDTO.getAddedAt());
			} else {
				System.out.println("Product find to WishList Failed");

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void findAllProductToWishList() {

		try {
			List<WishListDTO> wishListDTOs = wishListService.findAllWishList();
			if (wishListDTOs != null) {
				for (WishListDTO wishListDTO : wishListDTOs) {
					System.out.println("WishList ID:" + wishListDTO.getId());
					System.out.println("Product id in WishList :" + wishListDTO.getProductId());
					System.out.println("User id in WishList :" + wishListDTO.getUserId());
					System.out.println("WishList Created Date:" + wishListDTO.getAddedAt());
				}
			} else {
				System.out.println("Product find to WishList Failed");

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void main(String[] args) {
		DBUtil dbUtil=new DBUtil();
		WishListDAO wishListDAO= new WishListDAO(dbUtil);
		WishListService wishListService= new WishListService(wishListDAO);
		WishListMainServlet wishListMainServlet=new WishListMainServlet(wishListService);
		
		//wishListMainServlet.savetToWishList();
		wishListMainServlet.deleteToWishList();
		//wishListMainServlet.findProductToWishListById();
//		wishListMainServlet.findAllProductToWishList();
		
		
	}
}
