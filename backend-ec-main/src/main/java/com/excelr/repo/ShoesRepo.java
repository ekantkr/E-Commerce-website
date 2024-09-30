package com.excelr.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excelr.model.Shoes;
@Repository
public interface ShoesRepo extends JpaRepository<Shoes, Long> {
	public List<Shoes> findByCategory(String category);
	

}
