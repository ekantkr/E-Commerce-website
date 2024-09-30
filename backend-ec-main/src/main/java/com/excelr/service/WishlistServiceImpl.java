package com.excelr.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excelr.model.User;
import com.excelr.model.Wishlist;
import com.excelr.repo.WishlistRepository;

@Service
public class WishlistServiceImpl implements WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Override
    public List<Wishlist> getWishlistsByUser(User user) {
        return wishlistRepository.findByUser(user);
    }

    @Override
    public Wishlist addWishlist(Wishlist wishlist) {
        return wishlistRepository.save(wishlist);
    }

    @Override
    public void removeWishlist(Long wishlistId) {
        Optional<Wishlist> wishlist = wishlistRepository.findById(wishlistId);
        if (wishlist.isPresent()) {
            wishlistRepository.delete(wishlist.get());
        }
    }
}
