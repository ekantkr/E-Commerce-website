package com.excelr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.excelr.model.Cart;
import com.excelr.model.CartItemRequest;  // Import your CartItemRequest class
import com.excelr.service.CartService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/{userId}/add")
    public ResponseEntity<Cart> addToCart(
        @PathVariable Integer userId,
        @RequestBody CartItemRequest cartItemRequest) {
        Cart updatedCart = cartService.addToCart(userId, cartItemRequest.getShoesId(), cartItemRequest.getQuantity());
        return ResponseEntity.ok(updatedCart);
    }

    @DeleteMapping("/{userId}/remove/{cartItemId}")
    public ResponseEntity<Cart> removeFromCart(
        @PathVariable Integer userId,
        @PathVariable Long cartItemId) {
        Cart updatedCart = cartService.removeFromCart(userId, cartItemId);
        return ResponseEntity.ok(updatedCart);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCartByUser(@PathVariable Integer userId) {
        Cart cart = cartService.getCartByUser(userId);
        return ResponseEntity.ok(cart);
    }
}
