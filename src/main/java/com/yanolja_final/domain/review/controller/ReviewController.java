package com.yanolja_final.domain.review.controller;

import com.yanolja_final.domain.review.dto.request.CreateReviewRequest;
import com.yanolja_final.domain.review.dto.response.ReviewResponse;
import com.yanolja_final.domain.review.facade.ReviewFacade;
import com.yanolja_final.global.config.argumentresolver.LoginedUserId;
import com.yanolja_final.global.util.PaginationUtils;
import com.yanolja_final.global.util.ResponseDTO;

import java.util.Map;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/packages")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewFacade reviewFacade;

    @PostMapping("/{packageId}/reviews")
    public ResponseDTO<ReviewResponse> createReview(@PathVariable Long packageId,
        @RequestBody CreateReviewRequest request,
        @LoginedUserId Long userId) {
        ReviewResponse response = reviewFacade.createReview(packageId, request, userId);
        return ResponseDTO.okWithData(response);
    }

    @DeleteMapping("/{packageId}/reviews/{reviewId}")
    public ResponseDTO<Void> deleteReview(@PathVariable Long packageId, @PathVariable Long reviewId,@LoginedUserId Long userId) {
        reviewFacade.deleteReview(packageId, reviewId,userId);
        return ResponseDTO.ok();
    }

    @GetMapping("/reviews")
    public ResponseEntity<ResponseDTO<Map<String, Object>>> getUserReviews(
        @LoginedUserId Long userId,
        @PageableDefault(size = 5) Pageable pageable) {

        Page<ReviewResponse> reviewsPage = reviewFacade.getUserReviews(userId, pageable);
        Map<String, Object> response = PaginationUtils.createPageResponse(reviewsPage);

        return ResponseEntity.ok(ResponseDTO.okWithData(response));
    }
}
