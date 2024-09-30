package com.excelr.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.excelr.Enum.OrderStatus;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;

    @Column(name = "order_date")
    private LocalDateTime orderDate;	

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "total_amount")
    private double totalAmount;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "order")
//    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Payment payment;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipping_details_id")
    private ShippingDetails shippingDetails;

    public void addPayment(Payment payment) {
        this.payment = payment;
        payment.setOrder(this); // Establish the bidirectional relationship
    }

    public void setShippingDetails(ShippingDetails shippingDetails) {
        this.shippingDetails = shippingDetails;
        if (shippingDetails != null) {
            shippingDetails.setOrder(this); // Ensure bidirectional relationship
        }
    }
}
