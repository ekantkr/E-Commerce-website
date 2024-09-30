package com.excelr.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excelr.model.Cart;
import com.excelr.model.User;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {

	Optional<Cart> findByUser(User user);

}
