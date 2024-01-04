package com.yanolja_final.domain.review.repository;

import com.yanolja_final.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
