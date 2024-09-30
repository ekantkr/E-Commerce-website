package com.excelr.model;

public class CartItemRequest {
	
	private Long shoesId;
    private int quantity;
	public Long getShoesId() {
		return shoesId;
	}
	public void setShoesId(Long shoesId) {
		this.shoesId = shoesId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "CartItemRequest [shoesId=" + shoesId + ", quantity=" + quantity + "]";
	}
    

}
