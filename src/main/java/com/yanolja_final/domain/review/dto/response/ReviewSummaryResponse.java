package com.yanolja_final.domain.review.dto.response;

import com.yanolja_final.domain.review.entity.Review;
import java.util.List;

public record ReviewSummaryResponse(
    int count,
    double averageStars,
    int averageProductScore,
    int averageScheduleScore,
    int averageGuideScore,
    int averageAppointmentScore
) {
    public static ReviewSummaryResponse fromReviews(List<Review> reviews) {
        int count = reviews.size();

        if (count == 0) {
            return new ReviewSummaryResponse(0, 0.0, 0, 0, 0, 0);
        }

        int averageProductScore = (int) Math.round(reviews.stream().mapToInt(Review::getProductScore).average().orElse(0.0));
        int averageScheduleScore = (int) Math.round(reviews.stream().mapToInt(Review::getScheduleScore).average().orElse(0.0));
        int averageGuideScore = (int) Math.round(reviews.stream().mapToInt(Review::getGuideScore).average().orElse(0.0));
        int averageAppointmentScore = (int) Math.round(reviews.stream().mapToInt(Review::getAppointmentScore).average().orElse(0.0));

        double averageStars = Math.round((averageProductScore + averageScheduleScore + averageGuideScore + averageAppointmentScore) / 4.0 * 10) / 10.0;

        return new ReviewSummaryResponse(
            count, averageStars, averageProductScore, averageScheduleScore, averageGuideScore, averageAppointmentScore
        );
    }
}
