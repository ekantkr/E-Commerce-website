//package com.excelr.service;
//
//import java.util.List;
//
//import com.excelr.Exception.ReviewException;
//import com.excelr.model.Review;
//import com.excelr.model.Shoes;
//import com.excelr.model.User;
//import com.excelr.repo.ReviewRepository;
//import com.excelr.repo.ShoesRepo;
//import com.excelr.repo.UserRepo;
//
//public class ReviewServiceImpl implements ReviewService{
//	
//	private  ShoesRepo shoesRepo;
//
//	private ReviewRepository reviewRepository;
//
//	private UserRepo userRepo;
//
//	@Override
//	public Review addReviewToShoes(Integer shoesId, Integer userId, Review review) throws ReviewException {
//		Shoes existingShoes = shoesRepository.findById(shoesId)
//				.orElseThrow(() -> new ReviewException("Shoes Not Found"));
//
//		User existingUser = userRepository.findById(userId)
//				.orElseThrow(() -> new ReviewException("User Not Found In Database"));
//
//		existingUser.getReviews().add(review);
//		review.setUser(existingUser);
//		existingShoes.getReviews().add(review);
//		review.setShoes(existingShoes);
//		userRepository.save(existingUser);
//		shoesRepository.save(existingShoes);
//
//		return reviewRepository.save(review);
//	}
//
//	@Override
//	public Review updateReviewToShoes(Integer reviewId, Review review) throws ReviewException {
//		Review existingReview = reviewRepository.findById(reviewId)
//				.orElseThrow(() -> new ReviewException("Review With Id " + reviewId + " Not Found In Database"));
//
//		existingReview.setComment(review.getComment());
//		existingReview.setRating(review.getRating());
//		return reviewRepository.save(existingReview);
//	}
//
//	@Override
//	public void deleteReview(Integer reviewId) throws ReviewException {
//		Review existingReview = reviewRepository.findById(reviewId)
//				.orElseThrow(() -> new ReviewException("Review With Id " + reviewId + " Not Found In Database"));
//
//		reviewRepository.delete(existingReview);
//	}
//
//	@Override
//	public List<Review> getAllReviewsOfShoes(Integer shoesId) throws ReviewException {
//		Shoes existingShoes = shoesRepo.findById(shoesId)
//				.orElseThrow(() -> new ReviewException("Invalid Shoes id"));
//
//		List<Review> allReviewsByShoesId = reviewRepository.findAllReviewsByShoesId(shoesId);
//		if (allReviewsByShoesId.isEmpty()) {
//			throw new ReviewException("No Review Of Given Shoes is Available");
//		}
//		return allReviewsByShoesId;
//	}
//
//}