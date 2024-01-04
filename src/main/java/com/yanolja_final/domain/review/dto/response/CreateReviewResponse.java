package com.yanolja_final.domain.review.dto.response;

import com.yanolja_final.domain.review.entity.Review;

    public record CreateReviewResponse (
        Long reviewId,
        String content,
        String createdAt,
        double averageStars,
        int productScore,
        int scheduleScore,
        int guideScore,
        int appointmentScore
    ) {
        public static CreateReviewResponse fromReview(Review review) {
            double averageStars = (review.getProductScore() + review.getScheduleScore() +
                review.getGuideScore() + review.getAppointmentScore()) / 4.0;

            return new CreateReviewResponse(
                review.getId(),
                review.getContent(),
                review.getFormattedCreatedAt(),
                Math.round(averageStars * 10) / 10.0,
                review.getProductScore(),
                review.getScheduleScore(),
                review.getGuideScore(),
                review.getAppointmentScore()
            );
        }
    }
