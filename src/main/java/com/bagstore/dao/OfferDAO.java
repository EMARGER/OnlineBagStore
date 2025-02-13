package com.bagstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bagstore.dto.OfferDTO;
import com.bagstore.util.DBUtil;

public class OfferDAO {
	private final String Q_SAVE_OFFER = "insert into offer(product_id,discount,strat_date,end_date) values(?,?,?,?)";
	private final String Q_UPDATE_BY_ID = "update offer set product_id=?,discount=?,strat_date=?,end_date=? where id=?";
	private final String Q_DELETE_BY_ID = "delete from offer where id=?";
	private final String Q_FIND_BY_ID = "select * from offer where id=?";
	private final String Q_FIND_ALL = "select * from offer";

	DBUtil dbUtil = new DBUtil();

	public OfferDAO(DBUtil dbUtil) {
		this.dbUtil = dbUtil;
	}

	Connection connection = null;
	PreparedStatement pstmt = null;

	public int saveOffer(OfferDTO offerDTO) throws Exception {
		try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(Q_SAVE_OFFER);
			pstmt.setInt(1, offerDTO.getProductId());
			pstmt.setDouble(2, offerDTO.getDiscount());
			pstmt.setDate(3, offerDTO.getStartDate());
			pstmt.setDate(4, offerDTO.getEndDate());
			int count = pstmt.executeUpdate();
			return count;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		} finally {
			dbUtil.close(connection, pstmt);
		}
	}

	public int updateOfferById(OfferDTO offerDTO) throws Exception {
		try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(Q_UPDATE_BY_ID);
			pstmt.setInt(1, offerDTO.getId());
			pstmt.setDouble(2, offerDTO.getDiscount());
			pstmt.setDate(3, offerDTO.getStartDate());
			pstmt.setDate(4, offerDTO.getEndDate());
			pstmt.setInt(5, offerDTO.getId());
			int count = pstmt.executeUpdate();
			return count;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		} finally {
			dbUtil.close(connection, pstmt);
		}
	}

	public int deleteOfferById(Integer id) throws Exception {
		try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(Q_DELETE_BY_ID);
			pstmt.setInt(1, id);
			int count = pstmt.executeUpdate();
			return count;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		} finally {
			dbUtil.close(connection, pstmt);
		}
	}

	public List<OfferDTO> findAllOffer() throws Exception {
		try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(Q_FIND_ALL);
			List<OfferDTO> offerDTOs = new ArrayList<>();
			OfferDTO offerDTO = null;
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				offerDTO = new OfferDTO();
				offerDTO.setId(rs.getInt("id"));
				offerDTO.setProductId(rs.getInt("product_id"));
				offerDTO.setDiscount(rs.getDouble("discount"));
				offerDTO.setStartDate(rs.getDate("strat_date"));
				offerDTO.setEndDate(rs.getDate("end_date"));
				offerDTOs.add(offerDTO);
			}
			return offerDTOs;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		} finally {
			dbUtil.close(connection, pstmt);
		}
	}

	public OfferDTO findOfferById(Integer id) throws Exception {
		try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(Q_FIND_BY_ID);
			pstmt.setInt(1, id);
			OfferDTO offerDTO = null;
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				offerDTO = new OfferDTO();
				offerDTO.setId(rs.getInt("id"));
				offerDTO.setProductId(rs.getInt("product_id"));
				offerDTO.setDiscount(rs.getDouble("discount"));
				offerDTO.setStartDate(rs.getDate("strat_date"));
				offerDTO.setEndDate(rs.getDate("end_date"));
			}
			return offerDTO;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		} finally {
			dbUtil.close(connection, pstmt);
		}
	}

}
