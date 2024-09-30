package com.excelr.model;

import java.time.LocalDateTime;

import com.excelr.Enum.OrderStatus;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class OrderRequest {
	@NotNull(message = "User ID cannot be null")
    private Integer userId;

    @Positive(message = "Total amount must be positive")
    private Double totalAmount;

    private OrderStatus status;

    private LocalDateTime orderDate;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public String toString() {
		return "OrderRequest [userId=" + userId + ", totalAmount=" + totalAmount + ", status=" + status + ", orderDate="
				+ orderDate + "]";
	}
    
    

}
