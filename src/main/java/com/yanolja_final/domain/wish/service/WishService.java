package com.yanolja_final.domain.wish.service;

import com.yanolja_final.domain.packages.entity.Package;
import com.yanolja_final.domain.packages.exception.PackageNotFoundException;
import com.yanolja_final.domain.packages.repository.PackageRepository;
import com.yanolja_final.domain.user.entity.User;
import com.yanolja_final.domain.user.exception.UserNotFoundException;
import com.yanolja_final.domain.user.repository.UserRepository;
import com.yanolja_final.domain.wish.entity.Wish;
import com.yanolja_final.domain.wish.exception.WishNotFoundException;
import com.yanolja_final.domain.wish.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class WishService {

    private final WishRepository wishRepository;
    private final PackageRepository packageRepository;
    private final UserRepository userRepository;
    public Wish createWish(Long packageId, Long userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(""));
        Package aPackage = packageRepository.findById(packageId).orElseThrow(() -> new PackageNotFoundException());

        Wish wish = new Wish(user, aPackage);
        return wishRepository.save(wish);
    }

    public void deleteWish(Long wishId, Long userId) {

        wishRepository.findByIdAndUserId(wishId, userId)
            .orElseThrow(() -> new WishNotFoundException());

        wishRepository.deleteById(wishId);
    }
}
