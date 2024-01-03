package com.yanolja_final.domain.wish.repository;

import com.yanolja_final.domain.wish.entity.Wish;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface WishRepository extends JpaRepository<Wish, Long> {
    Optional<Wish> findByIdAndUserId(Long wishId, Long userId);

    Page<Wish> findByUserId(Long userId, Pageable pageable);
}
