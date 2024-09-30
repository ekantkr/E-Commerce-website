package com.excelr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shoes_id")
    private Shoes shoes;

    private int quantity;
    
 

    @ManyToOne
    @JoinColumn(name = "cart_id")
    @JsonIgnore
    private Cart cart;

    @Override
    public String toString() {
        return "CartItem [id=" + id + ", shoes=" + shoes + ", quantity=" + quantity + "]";
    }
}
