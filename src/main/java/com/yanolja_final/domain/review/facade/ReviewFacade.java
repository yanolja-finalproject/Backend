package com.yanolja_final.domain.review.facade;

import com.yanolja_final.domain.order.entity.Order;
import com.yanolja_final.domain.order.service.OrderService;
import com.yanolja_final.domain.review.dto.request.CreateReviewRequest;
import com.yanolja_final.domain.review.dto.response.ReviewResponse;
import com.yanolja_final.domain.review.dto.response.ReviewSummaryResponse;
import com.yanolja_final.domain.review.entity.Review;
import com.yanolja_final.domain.review.exception.ReviewAlreadyRegisteredException;
import com.yanolja_final.domain.review.exception.UnauthorizedReviewAccessException;
import com.yanolja_final.domain.review.service.ReviewService;
import com.yanolja_final.domain.user.entity.User;
import com.yanolja_final.domain.user.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewFacade {

    private final ReviewService reviewService;
    private final UserService userService;
    private final OrderService orderService;

    public ReviewResponse createReview(Long orderId, CreateReviewRequest request, Long userId) {
        Order order = orderService.findById(orderId);

        if (!order.getUser().getId().equals(userId)) {
            throw new UnauthorizedReviewAccessException();
        }
        if (order.getReview() != null) {
            throw new ReviewAlreadyRegisteredException();
        }
        User user = userService.findById(userId);
        Review review = reviewService.createReview(order, user, request);

        return ReviewResponse.fromReview(review);
    }


    public void deleteReview(Long reviewId, Long userId) {
        reviewService.deleteReview(reviewId, userId);
    }

    public Page<ReviewResponse> getUserReviews(Long userId, Pageable pageable) {
        return reviewService.getUserReviews(userId, pageable);
    }

    public ReviewSummaryResponse getPackageReviewsSummary(Long packageId) {
        List<Review> reviews = reviewService.findReviewsByPackageId(packageId);
        return ReviewSummaryResponse.fromReviews(reviews);
    }
}
