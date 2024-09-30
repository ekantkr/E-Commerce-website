package com.excelr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "shipping_details")
public class ShippingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipping_id")
    private int shippingId;

    @NotNull(message = "Address is mandatory, cannot be null")
    @NotBlank(message = "Address is mandatory")
    @Column(name = "address")
    private String address;

    @NotNull(message = "City is mandatory, cannot be null")
    @NotBlank(message = "City is mandatory")
    @Column(name = "city")
    private String city;

    @NotNull(message = "State is mandatory, cannot be null")
    @NotBlank(message = "State is mandatory")
    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @NotNull(message = "Postal code is mandatory, cannot be null")
    @NotBlank(message = "Postal code is mandatory")
    @Column(name = "postal_code")
    private String postalCode;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipper_id", nullable = false)
    private Shipper shipper;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Orders order; // Changed from `orders` to `order` for consistency

    // Utility method to set order
    public void setOrder(Orders order) {
        this.order = order;
        if (order != null && order.getShippingDetails() != this) {
            order.setShippingDetails(this); // Ensure bidirectional relationship
        }
    }
}
