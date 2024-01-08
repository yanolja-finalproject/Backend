package com.yanolja_final.domain.review.facade;

import com.yanolja_final.domain.packages.entity.Package;
import com.yanolja_final.domain.packages.service.PackageService;
import com.yanolja_final.domain.review.dto.request.CreateReviewRequest;
import com.yanolja_final.domain.review.dto.response.ReviewResponse;
import com.yanolja_final.domain.review.entity.Review;
import com.yanolja_final.domain.review.service.ReviewService;
import com.yanolja_final.domain.user.entity.User;
import com.yanolja_final.domain.user.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewFacade {

    private final ReviewService reviewService;
    private final PackageService packageService;
    private final UserService userService;

    public ReviewResponse createReview(Long packageId, CreateReviewRequest request, Long userId) {
        Package aPackage = packageService.findById(packageId);
        User user = userService.findById(userId);

        Review review = reviewService.createReview(aPackage, user, request);
        return ReviewResponse.fromReview(review);
    }

    public void deleteReview(Long reviewId, Long userId) {
        reviewService.deleteReview(reviewId, userId);
    }

    public Page<ReviewResponse> getUserReviews(Long userId, Pageable pageable) {
        return reviewService.getUserReviews(userId, pageable);
    }
}
