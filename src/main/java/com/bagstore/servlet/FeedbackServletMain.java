package com.bagstore.servlet;

import java.util.List;

import com.bagstore.dao.FeedbackDAO;
import com.bagstore.dto.FeedbackDTO;
import com.bagstore.service.FeedbackService;
import com.bagstore.util.DBUtil;

public class FeedbackServletMain {
	private FeedbackService feedbackService;

	public FeedbackServletMain(FeedbackService feedbackService) {
		this.feedbackService = feedbackService;
	}

	public void save() {
		FeedbackDTO feedbackDTO = new FeedbackDTO();
		feedbackDTO.setUserId(6);
		feedbackDTO.setProductId(2);
		feedbackDTO.setRating(2);
		feedbackDTO.setComment("very Cheap Quality");

		try {
			int count = feedbackService.save(feedbackDTO);

			if (count > 0) {
				System.out.println("Feedback Saved Succesfully");
			} else {
				System.out.println("Failed to save feedback");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update() {
		FeedbackDTO feedbackDTO = new FeedbackDTO();
		feedbackDTO.setUserId(12);
		feedbackDTO.setProductId(3);
		feedbackDTO.setRating(4);
		feedbackDTO.setComment("Value for money");
		feedbackDTO.setId(1);

		try {
			int count = feedbackService.update(feedbackDTO);

			if (count > 0) {
				System.out.println("Feedback Update Succesfully");
			} else {
				System.out.println("Failed to Upadte feedback");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete() {
		int id = 1;

		try {
			int count = feedbackService.delete(id);

			if (count > 0) {
				System.out.println("Feedback Deleted Succesfully");
			} else {
				System.out.println("Failed to Delete feedback");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void findById() {
		int id = 2;
		try {
			FeedbackDTO feedbackDTO = feedbackService.findById(id);

			if (feedbackDTO != null) {
				System.out.println("Feedback Found");
				System.out.println("id : " + feedbackDTO.getId());
				System.out.println("User-Id : " + feedbackDTO.getUserId());
				System.out.println("Product-Id: " + feedbackDTO.getProductId());
				System.out.println("Rating: " + feedbackDTO.getRating());
				System.out.println("Comment: " + feedbackDTO.getComment());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void findAll() {

		try {
			List<FeedbackDTO> feedbackDTOs = feedbackService.findAll();

			if (feedbackDTOs != null) {
				System.out.println("Feedbacks Found");
				for (FeedbackDTO feedbackDTO : feedbackDTOs) {
					System.out.println("id : " + feedbackDTO.getId());
					System.out.println("User-Id : " + feedbackDTO.getUserId());
					System.out.println("Product-Id: " + feedbackDTO.getProductId());
					System.out.println("Rating: " + feedbackDTO.getRating());
					System.out.println("Comment: " + feedbackDTO.getComment());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void findByProductId() {
		int productId = 3;
		try {
			List<FeedbackDTO> feedbackDTOs = feedbackService.findByProductId(productId);

			if (feedbackDTOs != null) {
				System.out.println("Feedbacks Found");
				for (FeedbackDTO feedbackDTO : feedbackDTOs) {
					System.out.println("id : " + feedbackDTO.getId());
					System.out.println("User-Id : " + feedbackDTO.getUserId());
					System.out.println("Product-Id: " + feedbackDTO.getProductId());
					System.out.println("Rating: " + feedbackDTO.getRating());
					System.out.println("Comment: " + feedbackDTO.getComment());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void findByUserId() {
		int userId = 6;
		try {
			List<FeedbackDTO> feedbackDTOs = feedbackService.findByUserId(userId);

			if (feedbackDTOs != null) {
				System.out.println("Feedbacks Found");
				for (FeedbackDTO feedbackDTO : feedbackDTOs) {
					System.out.println("id : " + feedbackDTO.getId());
					System.out.println("User-Id : " + feedbackDTO.getUserId());
					System.out.println("Product-Id: " + feedbackDTO.getProductId());
					System.out.println("Rating: " + feedbackDTO.getRating());
					System.out.println("Comment: " + feedbackDTO.getComment());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		DBUtil dbUtil = new DBUtil();
		FeedbackDAO feedbackDAO = new FeedbackDAO(dbUtil);
		FeedbackService feedbackService = new FeedbackService(feedbackDAO);
		FeedbackServletMain mainServlet = new FeedbackServletMain(feedbackService);
//		mainServlet.save();
//		mainServlet.update();
//		mainServlet.delete();
//		mainServlet.findById();
//		mainServlet.findAll();
//		mainServlet.findByProductId();
		mainServlet.findByUserId();
	}
}
