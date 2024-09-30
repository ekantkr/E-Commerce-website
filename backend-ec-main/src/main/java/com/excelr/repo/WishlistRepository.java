package com.excelr.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.excelr.model.User;
import com.excelr.model.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
	 List<Wishlist> findByUser(User user);

}
