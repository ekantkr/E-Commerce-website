package com.excelr.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.excelr.DTO.ShoeIdDto;
import com.excelr.model.Shoes;
import com.excelr.model.User;
import com.excelr.model.Wishlist;
import com.excelr.repo.ShoesRepo;
import com.excelr.service.WishlistService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/wishlists")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @Autowired
    private ShoesRepo shoesRepo;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Wishlist>> getWishlistsByUser(@PathVariable Integer userId) {
        User user = new User();
        user.setUserId(userId);
        List<Wishlist> wishlists = wishlistService.getWishlistsByUser(user);
        return new ResponseEntity<>(wishlists, HttpStatus.OK);
    }

    @PostMapping("/{userId}/add")
    public ResponseEntity<Wishlist> addWishlist(@PathVariable Integer userId, @RequestBody ShoeIdDto shoeIdDto) {
        Long shoeId = shoeIdDto.getShoeId();

        User user = new User();
        user.setUserId(userId);

        Wishlist wishlist = wishlistService.getWishlistsByUser(user).stream()
            .findFirst()
            .orElse(new Wishlist());

        Optional<Shoes> shoeOptional = shoesRepo.findById(shoeId);
        if (!shoeOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Shoes shoe = shoeOptional.get();

        wishlist.setUser(user);
        if (wishlist.getShoes() == null) {
            wishlist.setShoes(new ArrayList<>());
        }

        if (!wishlist.getShoes().contains(shoe)) {
            wishlist.getShoes().add(shoe);
            wishlistService.addWishlist(wishlist);
        }

        return new ResponseEntity<>(wishlist, HttpStatus.CREATED);
    }

    @DeleteMapping("/{wishlistId}")
    public ResponseEntity<Void> removeWishlist(@PathVariable Long wishlistId) {
        wishlistService.removeWishlist(wishlistId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
