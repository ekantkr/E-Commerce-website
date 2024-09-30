package com.excelr.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
@Entity
@Table(name = "shipper")
public class Shipper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shipperId;

    @NotNull(message = "Name is mandatory, cannot be null")
    @NotBlank(message = "Name is mandatory")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Phone number is mandatory, cannot be null")
    @NotBlank(message = "Phone number is mandatory")
    @Size(min = 10, max = 12)
    @Column(name = "phone_number")
    private String phoneNumber;

    @JsonIgnore
    @OneToMany(mappedBy = "shipper", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ShippingDetails> shippingDetails = new ArrayList<>();
}
