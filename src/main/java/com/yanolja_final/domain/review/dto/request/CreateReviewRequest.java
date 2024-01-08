package com.yanolja_final.domain.review.dto.request;

import com.yanolja_final.domain.order.entity.Order;
import com.yanolja_final.domain.review.entity.Review;
import com.yanolja_final.domain.user.entity.User;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateReviewRequest(
    @NotNull(message = "리뷰 내용을 입력해 주세요.")
    String content,

    @NotNull(message = "점수를 입력해 주세요.")
    @Min(value = 1, message = "점수는 최소 1점이어야 합니다.")
    @Max(value = 5, message = "점수는 최대 5점까지 가능합니다.")
    int productScore,

    @NotNull(message = "점수를 입력해 주세요.")
    @Min(value = 1, message = "점수는 최소 1점이어야 합니다.")
    @Max(value = 5, message = "점수는 최대 5점까지 가능합니다.")
    int scheduleScore,

    @NotNull(message = "점수를 입력해 주세요.")
    @Min(value = 1, message = "점수는 최소 1점이어야 합니다.")
    @Max(value = 5, message = "점수는 최대 5점까지 가능합니다.")
    int guideScore,

    @NotNull(message = "점수를 입력해 주세요.")
    @Min(value = 1, message = "점수는 최소 1점이어야 합니다.")
    @Max(value = 5, message = "점수는 최대 5점까지 가능합니다.")
    int appointmentScore
) {
    public Review toReview(Order order, User user) {
        return Review.builder()
            .order(order)
            .user(user)
            .content(this.content)
            .productScore(this.productScore)
            .scheduleScore(this.scheduleScore)
            .guideScore(this.guideScore)
            .appointmentScore(this.appointmentScore)
            .build();
    }
}
