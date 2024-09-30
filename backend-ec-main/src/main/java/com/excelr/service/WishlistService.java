package com.excelr.service;

import java.util.List;
import com.excelr.model.User;
import com.excelr.model.Wishlist;

public interface WishlistService {
    List<Wishlist> getWishlistsByUser(User user);
    Wishlist addWishlist(Wishlist wishlist);
    void removeWishlist(Long wishlistId);
}
