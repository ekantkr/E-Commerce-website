package com.excelr.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excelr.model.User;
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	
//    Optional<User> findByEmail(String email);
    Optional<User> findById(Integer id);
	void deleteById(Integer id);
	public   Optional<User> findByEmail(String email);

}
