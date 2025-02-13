package com.bagstore.dto;

import java.sql.Date;

public class CartDTO {
	private int id;
	private int productId;
	private int userId;
	private int quntity;
	private Date addedAt;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getQuntity() {
		return quntity;
	}
	public void setQuntity(int quntity) {
		this.quntity = quntity;
	}
	public Date getAddedAt() {
		return addedAt;
	}
	public void setAddedAt(Date addedAt) {
		this.addedAt = addedAt;
	}

}
