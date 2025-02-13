package com.bagstore.servlet;

import java.sql.Date;
import java.util.List;

import com.bagstore.dao.OfferDAO;
import com.bagstore.dto.OfferDTO;
import com.bagstore.service.OfferService;
import com.bagstore.util.DBUtil;

public class OfferMainServlet {
	private OfferService offerService;

	public OfferMainServlet(OfferService offerService) {
		this.offerService = offerService;
	}

	public void saveOffer() {
		OfferDTO offerDTO = new OfferDTO();
		offerDTO.setProductId(3);
		offerDTO.setDiscount(10);
		offerDTO.setStartDate(Date.valueOf("2025-01-27"));
		offerDTO.setEndDate(Date.valueOf("2025-01-30"));
		try {
			int count = offerService.saveOffer(offerDTO);
			if (count > 0) {
				System.out.println("Offer Save to successfully");
			}else {
				System.out.println("Offer Failed to save");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	public void updateOffer() {
		OfferDTO offerDTO = new OfferDTO();
		offerDTO.setProductId(5);
		offerDTO.setDiscount(20);
		offerDTO.setStartDate(Date.valueOf("2025-02-13"));
		offerDTO.setEndDate(Date.valueOf("2025-02-20"));
		offerDTO.setId(3);
		try {
			int count = offerService.updateOffer(offerDTO);
			if (count > 0) {
				System.out.println("Offer update to successfully");
			}else {
				System.out.println("Offer Failed to update");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	public void deleteOffer() {
		Integer id =1;
		
		try {
			int count = offerService.deleteOffer(id);
			if (count > 0) {
				System.out.println("Offer deleted to successfully");
			}else {
				System.out.println("Offer Failed to delete");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	public void findeOfferById() {
		Integer id =2;
		
		try {
			OfferDTO offerDTO  = offerService.findOfferByID(id);
			if (offerDTO!=null) {
				System.out.println("Offer ID:"+offerDTO.getId());
				System.out.println("Offer Product id:"+offerDTO.getProductId());
				System.out.println("Offer Discount:"+offerDTO.getDiscount());
				System.out.println("Offer Start Date:"+offerDTO.getStartDate());
				System.out.println("Offer End Date:"+offerDTO.getEndDate());
			}else {
				System.out.println("Offer Failed to find");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void findeAllOffer() {
		
		
		try {
			List<OfferDTO> offerDTOs  = offerService.findAllOffer();
			if (offerDTOs !=null) {
				for (OfferDTO offerDTO : offerDTOs) {
					System.out.println("Offer ID:"+offerDTO.getId());
					System.out.println("Offer Product id:"+offerDTO.getProductId());
					System.out.println("Offer Discount:"+offerDTO.getDiscount());
					System.out.println("Offer Start Date:"+offerDTO.getStartDate());
					System.out.println("Offer End Date:"+offerDTO.getEndDate());
				}
			}else {
				System.out.println("Offer Failed to find");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	public static void main(String[] args) {
		DBUtil dbUtil= new DBUtil();
		OfferDAO offerDAO= new OfferDAO(dbUtil);
		OfferService offerService=new OfferService(offerDAO);
		OfferMainServlet offerMainServlet=new OfferMainServlet(offerService);
		//offerMainServlet.saveOffer();
		//offerMainServlet.updateOffer();
		offerMainServlet.deleteOffer();
    	//offerMainServlet.findeOfferById();
		//offerMainServlet.findeAllOffer();
		
	}

}
