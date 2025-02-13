package com.bagstore.service;

import java.util.List;

import com.bagstore.dao.FeedbackDAO;
import com.bagstore.dto.FeedbackDTO;

public class FeedbackService {
	private FeedbackDAO feedbackDAO;
	public FeedbackService(FeedbackDAO feedbackDAO) {
		this.feedbackDAO = feedbackDAO;
	}
	public int save(FeedbackDTO feedbackDTO) throws Exception {
		return feedbackDAO.save(feedbackDTO);
	}
	public int update(FeedbackDTO feedbackDTO) throws Exception {
		return feedbackDAO.update(feedbackDTO);
	}
	public int delete(int id) throws Exception {
		return feedbackDAO.delete(id);
	}
	public FeedbackDTO findById(int id) throws Exception {
		return feedbackDAO.findById(id);
	}
	public List<FeedbackDTO> findAll() throws Exception {
		return feedbackDAO.findAll();
	}
	public List<FeedbackDTO> findByProductId(int productId) throws Exception {
		return feedbackDAO.findByProductId(productId);
	}
	public List<FeedbackDTO> findByUserId(int userId) throws Exception {
		return feedbackDAO.findByUserId(userId);
	}
}
