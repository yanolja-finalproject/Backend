package com.yanolja_final.domain.wish.service;

import com.yanolja_final.domain.packages.entity.Package;
import com.yanolja_final.domain.user.entity.User;
import com.yanolja_final.domain.wish.dto.response.WishListResponse;
import com.yanolja_final.domain.wish.entity.Wish;
import com.yanolja_final.domain.wish.exception.WishNotFoundException;
import com.yanolja_final.domain.wish.repository.WishRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class WishService {

    private final WishRepository wishRepository;

    public Wish createWish(Package aPackage, User user) {
        return wishRepository.save(new Wish(user, aPackage));
    }

    public void deleteWish(Long wishId, Long userId) {
        Wish wish = wishRepository.findByIdAndUserId(wishId, userId).orElseThrow(() ->
            new WishNotFoundException());
        wishRepository.delete(wish);
    }

    @Transactional(readOnly = true)
    public Page<WishListResponse> getUserWishes(Long userId, Pageable pageable) {
        return wishRepository.findByUserId(userId, pageable).map(WishListResponse::new);
    }
}
