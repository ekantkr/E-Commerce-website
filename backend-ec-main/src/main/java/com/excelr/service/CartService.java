package com.excelr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excelr.model.Cart;
import com.excelr.model.Shoes;
import com.excelr.model.User;
import com.excelr.repo.CartRepo;
import com.excelr.repo.ShoesRepo;
import com.excelr.repo.UserRepo;

@Service
@Transactional
public class CartService {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private ShoesRepo shoesRepo;

    @Autowired
    private UserRepo userRepo;

    public Cart getCartByUser(Integer userId) {
        // Use the correct repository instance
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with ID " + userId + " not found"));
        
        // Fetch or create a Cart for the user
        Cart cart = cartRepo.findByUser(user)
                .orElseGet(() -> createCartForUser(user));
        
        return cart;
    }

    private Cart createCartForUser(User user) {
        Cart cart = new Cart();
        cart.setUser(user);  // Ensure this matches the field name in Cart entity
        // Save the Cart to persist the new relationship
        return cartRepo.save(cart);
    }

    public Cart addToCart(Integer userId, Long shoesId, int quantity) {
        // Fetch the Cart for the user
        Cart cart = getCartByUser(userId);

        // Fetch the Shoes entity
        Shoes shoes = shoesRepo.findById(shoesId)
                .orElseThrow(() -> new RuntimeException("Shoes with ID " + shoesId + " not found"));

        // Add item to Cart and save
        cart.addItem(shoes, quantity);
        return cartRepo.save(cart);
    }

    public Cart removeFromCart(Integer userId, Long cartItemId) {
        // Fetch the Cart for the user
        Cart cart = getCartByUser(userId);

        // Remove item from Cart and save
        cart.removeItem(cartItemId);
        return cartRepo.save(cart);
    }
}
