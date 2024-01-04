package com.yanolja_final.domain.wish.facade;

import com.yanolja_final.domain.packages.entity.Package;
import com.yanolja_final.domain.packages.service.PackageService;
import com.yanolja_final.domain.user.entity.User;
import com.yanolja_final.domain.user.service.UserService;
import com.yanolja_final.domain.wish.dto.response.WishListResponse;
import com.yanolja_final.domain.wish.entity.Wish;
import com.yanolja_final.domain.wish.service.WishService;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishFacade {

    private final WishService wishService;
    private final PackageService packageService;
    private final UserService userService;

    public Wish createWish(Long packageId, Long userId) {
        Package aPackage = packageService.findById(packageId);
        User user = userService.findById(userId);
        return wishService.createWish(aPackage, user);
    }

    public void deleteWish(Long wishId, Long userId) {
        wishService.deleteWish(wishId, userId);
    }

    public Page<WishListResponse> getUserWishes(Long userId, Pageable pageable) {
        return wishService.getUserWishes(userId, pageable);
    }
}
