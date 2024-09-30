//package com.excelr.repo;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import com.excelr.model.Review;
//@Repository
//public interface ReviewRepository extends JpaRepository<Review, Long> {
//	
//	@Query("SELECT r FROM Review r WHERE r.shoes.shoesId = :shoesId")
//	List<Review> findAllReviewsByProductId(@Param("shoesId") Integer shoesId);
//
//}
