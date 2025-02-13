package com.bagstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bagstore.dto.FeedbackDTO;
import com.bagstore.util.DBUtil;

public class FeedbackDAO {
	private DBUtil dbUtil;

	private final String Q_Feedback_Insert = "insert into feedback (user_id, product_id, rating , comment) values (?,?,?,?)";
	private final String Q_Feedback_Update_By_Id = "update feedback set user_id = ?, product_id = ?, rating = ?, comment = ? where id = ?";
	private final String Q_Feedback_Delete_By_Id = "delete from feedback where id = ?";
	private final String Q_Feedback_Find_By_Id = "select * from feedback where id= ?";
	private final String Q_Feedback_Find_All = "select * from feedback";
	private final String Q_Feedback_Find_By_Product_Id = "select * from feedback where product_id = ? ";
	private final String Q_Feedback_Find_By_User_Id = "select * from feedback where user_id = ?";


	public FeedbackDAO(DBUtil dbUtil) {
		this.dbUtil = dbUtil;
	}

	public int save(FeedbackDTO feedbackDTO) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dbUtil.getConnection();
			statement = connection.prepareStatement(Q_Feedback_Insert);
			statement.setInt(1, feedbackDTO.getUserId());
			statement.setInt(2, feedbackDTO.getProductId());
			statement.setInt(3, feedbackDTO.getRating());
			statement.setString(4, feedbackDTO.getComment());
			int count = statement.executeUpdate();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			dbUtil.close(connection, statement);
		}
	}
	public int update(FeedbackDTO feedbackDTO) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dbUtil.getConnection();
			statement = connection.prepareStatement(Q_Feedback_Update_By_Id);
			statement.setInt(1, feedbackDTO.getUserId());
			statement.setInt(2, feedbackDTO.getProductId());
			statement.setInt(3, feedbackDTO.getRating());
			statement.setString(4, feedbackDTO.getComment());
			statement.setInt(5, feedbackDTO.getId());
			int count = statement.executeUpdate();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			dbUtil.close(connection, statement);
		}
	}
	public int delete(int id) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dbUtil.getConnection();
			statement = connection.prepareStatement(Q_Feedback_Delete_By_Id);
			statement.setInt(1,id);
			int count = statement.executeUpdate();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			dbUtil.close(connection, statement);
		}
	}
	public FeedbackDTO findById(int id) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dbUtil.getConnection();
			statement = connection.prepareStatement(Q_Feedback_Find_By_Id);
			statement.setInt(1,id);
			ResultSet rs = statement.executeQuery();
			FeedbackDTO feedbackDTO = null;
			if(rs.next()) {
				feedbackDTO = new FeedbackDTO();
				feedbackDTO.setId(rs.getInt("id"));
				feedbackDTO.setUserId(rs.getInt("user_id"));
				feedbackDTO.setProductId(rs.getInt("product_id"));
				feedbackDTO.setRating(rs.getInt("rating"));
				feedbackDTO.setComment(rs.getString("comment"));
			}
			return feedbackDTO;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			dbUtil.close(connection, statement);
		}
	}
	public List<FeedbackDTO> findAll() throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dbUtil.getConnection();
			statement = connection.prepareStatement(Q_Feedback_Find_All);
			
			ResultSet rs = statement.executeQuery();
			FeedbackDTO feedbackDTO = null;
			List<FeedbackDTO> feedbackDTOs = new ArrayList<>();
			while(rs.next()) {
				feedbackDTO = new FeedbackDTO();
				feedbackDTO.setId(rs.getInt("id"));
				feedbackDTO.setUserId(rs.getInt("user_id"));
				feedbackDTO.setProductId(rs.getInt("product_id"));
				feedbackDTO.setRating(rs.getInt("rating"));
				feedbackDTO.setComment(rs.getString("comment"));
				
				feedbackDTOs.add(feedbackDTO);
			}
			return feedbackDTOs;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			dbUtil.close(connection, statement);
		}
	}
	public List<FeedbackDTO> findByProductId(int productId) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dbUtil.getConnection();
			statement = connection.prepareStatement(Q_Feedback_Find_By_Product_Id);
			statement.setInt(1, productId);
			ResultSet rs = statement.executeQuery();
			FeedbackDTO feedbackDTO = null;
			List<FeedbackDTO> feedbackDTOs = new ArrayList<>();
			while(rs.next()) {
				feedbackDTO = new FeedbackDTO();
				feedbackDTO.setId(rs.getInt("id"));
				feedbackDTO.setUserId(rs.getInt("user_id"));
				feedbackDTO.setProductId(rs.getInt("product_id"));
				feedbackDTO.setRating(rs.getInt("rating"));
				feedbackDTO.setComment(rs.getString("comment"));
				
				feedbackDTOs.add(feedbackDTO);
			}
			return feedbackDTOs;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			dbUtil.close(connection, statement);
		}
	}
	public List<FeedbackDTO> findByUserId(int userId) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dbUtil.getConnection();
			statement = connection.prepareStatement(Q_Feedback_Find_By_User_Id);
			statement.setInt(1, userId);
			ResultSet rs = statement.executeQuery();
			FeedbackDTO feedbackDTO = null;
			List<FeedbackDTO> feedbackDTOs = new ArrayList<>();
			while(rs.next()) {
				feedbackDTO = new FeedbackDTO();
				feedbackDTO.setId(rs.getInt("id"));
				feedbackDTO.setUserId(rs.getInt("user_id"));
				feedbackDTO.setProductId(rs.getInt("product_id"));
				feedbackDTO.setRating(rs.getInt("rating"));
				feedbackDTO.setComment(rs.getString("comment"));
				
				feedbackDTOs.add(feedbackDTO);
			}
			return feedbackDTOs;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			dbUtil.close(connection, statement);
		}
	}
}
