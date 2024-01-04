package com.yanolja_final.domain.review.dto.response;

import com.yanolja_final.domain.review.entity.Review;

    public record ReviewResponse(
        Long reviewId,
        String content,
        String createdAt,
        double averageStars,
        int productScore,
        int scheduleScore,
        int guideScore,
        int appointmentScore,
        String userNickname

    ) {
        public static ReviewResponse fromReview(Review review) {
            double averageStars = (review.getProductScore() + review.getScheduleScore() +
                review.getGuideScore() + review.getAppointmentScore()) / 4.0;

            String maskedNickname = maskNickname(review.getUser().getNickname());

            return new ReviewResponse(
                review.getId(),
                review.getContent(),
                review.getFormattedCreatedAt(),
                Math.round(averageStars * 10) / 10.0,
                review.getProductScore(),
                review.getScheduleScore(),
                review.getGuideScore(),
                review.getAppointmentScore(),
                maskedNickname
            );
        }
        private static String maskNickname(String nickname) {
            if (nickname.length() > 4) {
                String visiblePart = nickname.substring(0, nickname.length() - 4);
                return visiblePart + "****";
            } else {
                // 닉네임 길이가 4자 미만인 경우, 전체를 마스킹 처리
                return "****";
            }
        }

    }
