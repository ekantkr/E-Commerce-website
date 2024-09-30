package com.excelr.DTO;

import java.time.LocalDateTime;
import java.util.List;

import com.excelr.Enum.OrderStatus;
import com.excelr.model.Payment;
import com.excelr.model.ShippingDetails;
import com.fasterxml.jackson.annotation.JsonFormat;

public class OrdersDTO {

    private Integer orderId;
    private OrderStatus status;  
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime orderDate;  
    
    private Double totalAmount;   
    private Payment payment;         
    private ShippingDetails shippingDetails;  

   
    public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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
	
	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public ShippingDetails getShippingDetails() {
		return shippingDetails;
	}
	
	public void setShippingDetails(ShippingDetails shippingDetails) {
		this.shippingDetails = shippingDetails;
	}


	@Override
    public String toString() {
        return "OrdersDTO [orderId=" + orderId + ", status=" + status + ", orderDate=" + orderDate + ", totalAmount="
                + totalAmount + ", orderItems=" + ", payment=" + payment + ", shippingDetails=" + shippingDetails + "]";
    }
}
