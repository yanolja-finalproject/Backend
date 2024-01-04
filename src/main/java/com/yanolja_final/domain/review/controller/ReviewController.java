package com.yanolja_final.domain.review.controller;

import com.yanolja_final.domain.review.dto.request.CreateReviewRequest;
import com.yanolja_final.domain.review.dto.response.CreateReviewResponse;
import com.yanolja_final.domain.review.facade.ReviewFacade;
import com.yanolja_final.global.config.argumentresolver.LoginedUserId;

import com.yanolja_final.global.util.ResponseDTO;
import lombok.RequiredArgsConstructor;

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
    public ResponseDTO<CreateReviewResponse> createReview(@PathVariable Long packageId,
        @RequestBody CreateReviewRequest request,
        @LoginedUserId Long userId) {
        CreateReviewResponse response = reviewFacade.createReview(packageId, request, userId);
        return ResponseDTO.okWithData(response);
    }
}
