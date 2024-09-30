package com.excelr.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<CartItem> items = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "userId") // Ensure proper mapping to UserDtls
    @JsonBackReference
    private User user;

    public void addItem(Shoes shoes, int quantity) {
        CartItem cartItem = new CartItem();
        cartItem.setShoes(shoes);
        cartItem.setQuantity(quantity);
        cartItem.setCart(this);
        this.items.add(cartItem);
    }

    public void removeItem(Long cartItemId) {
        items.removeIf(item -> item.getId().equals(cartItemId));
    }

	@Override
	public String toString() {
		return "Cart [id=" + id + ", items=" + items + ", user=" + user + "]";
	}

//    @Override
//    public String toString() {
//        return "Cart [id=" + id + ", items=" + items + ", userDtls=" + userDtls + "]";
//    }
    
    
}
