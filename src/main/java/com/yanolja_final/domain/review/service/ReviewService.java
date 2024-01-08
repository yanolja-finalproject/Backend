package com.yanolja_final.domain.review.service;

import com.yanolja_final.domain.order.entity.Order;
import com.yanolja_final.domain.review.dto.request.CreateReviewRequest;
import com.yanolja_final.domain.review.dto.response.ReviewResponse;
import com.yanolja_final.domain.review.entity.Review;
import com.yanolja_final.domain.review.exception.ReviewNotFoundException;
import com.yanolja_final.domain.review.exception.UnauthorizedReviewDeletionException;
import com.yanolja_final.domain.review.repository.ReviewRepository;
import com.yanolja_final.domain.user.entity.User;

import jakarta.transaction.Transactional;

import java.util.List;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Review createReview(Order  order, User user, CreateReviewRequest request) {
        Review review = request.toReview(order, user);
        return reviewRepository.save(review);
    }

    public void deleteReview(Long reviewId, Long userId) {
        Review review = reviewRepository.findById(reviewId)
            .orElseThrow(() -> new ReviewNotFoundException());
        if (!review.getUser().getId().equals(userId)) {
            throw new UnauthorizedReviewDeletionException();
        }
        reviewRepository.deleteById(reviewId);
    }

    public Page<ReviewResponse> getUserReviews(Long userId, Pageable pageable) {
        return reviewRepository.findByUserId(userId, pageable).map(ReviewResponse::fromReview);
    }

    public List<Review> findReviewsByPackageId(Long packageId) {
        return reviewRepository.findReviewsByPackageId(packageId);
    }
}
