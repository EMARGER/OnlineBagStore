package com.bagstore.dto;

import java.sql.Date;

public class OrderItemDTO {
	private int id;
	private int productId;
	private int purchasOrderId;
	private int quantity;
	private double totalPrice;

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

	public int getPurchasOrderId() {
		return purchasOrderId;
	}

	public void setPurchasOrderId(int purchasOrderId) {
		this.purchasOrderId = purchasOrderId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

}
