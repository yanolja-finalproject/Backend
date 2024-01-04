package com.yanolja_final.domain.review.service;

import com.yanolja_final.domain.packages.entity.Package;
import com.yanolja_final.domain.review.dto.request.CreateReviewRequest;
import com.yanolja_final.domain.review.entity.Review;
import com.yanolja_final.domain.review.repository.ReviewRepository;
import com.yanolja_final.domain.user.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Review createReview(Package aPackage, User user, CreateReviewRequest request) {
        Review review = request.toReview(aPackage, user);
        return reviewRepository.save(review);
    }
}
