package com.excelr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.excelr.model.CartItem;
import com.excelr.repo.CartItemRepo;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepo cartItemRepo;

    public CartItem saveCartItem(CartItem cartItem) {
        return cartItemRepo.save(cartItem);
    }

    public void deleteCartItem(Long cartItemId) {
        cartItemRepo.deleteById(cartItemId);
    }

}
