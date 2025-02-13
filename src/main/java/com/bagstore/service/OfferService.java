package com.bagstore.service;

import java.util.List;

import com.bagstore.dao.OfferDAO;
import com.bagstore.dto.OfferDTO;

public class OfferService {
	
	private OfferDAO offerDAO;
	
	public OfferService(OfferDAO offerDAO) {
		this.offerDAO=offerDAO;
	}
	
	public int saveOffer(OfferDTO offerDTO) throws Exception {
		int count=offerDAO.saveOffer(offerDTO);
		return count;
	}
	
	public int updateOffer(OfferDTO offerDTO) throws Exception {
		int count=offerDAO.updateOfferById(offerDTO);
		return count;
	}

	public int deleteOffer(Integer id) throws Exception {
		int count=offerDAO.deleteOfferById(id);
		return count;
	}
	public OfferDTO findOfferByID(Integer id ) throws Exception {
	return offerDAO.findOfferById(id);	
	}
	public List<OfferDTO> findAllOffer() throws Exception {
		return offerDAO.findAllOffer();
		 
	}
}
