package com.yanolja_final.domain.wish.dto.response;

import com.yanolja_final.domain.packages.entity.Hashtag;
import com.yanolja_final.domain.packages.entity.PackageDepartureOption;
import com.yanolja_final.domain.wish.entity.Wish;

import java.util.List;
import java.util.stream.Collectors;

public record WishListResponse(
    Long packageId,
    Long wishId,
    String imageUrl,
    String nationName,
    String title,
    List<String> hashtags,
    Integer minPrice,
    Integer lodgeDays,
    Integer tripDays) {

    public WishListResponse(Wish wish) {
        this(
            wish.getAPackage().getId(),
            wish.getId(),
            null, // TODO 상품 소개 이미지 여러 개로 늘어났습니다
            wish.getAPackage().getNationName(),
            wish.getAPackage().getTitle(),
            wish.getAPackage().getHashtags().stream().map(Hashtag::getName).collect(Collectors.toList()),
            wish.getAPackage().getAvailableDates().stream().mapToInt(PackageDepartureOption::getAdultPrice).min().orElse(0),
            wish.getAPackage().getLodgeDays(),
            wish.getAPackage().getTripDays()
        );
    }
}
