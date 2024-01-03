package com.yanolja_final.domain.wish.repository;

import com.yanolja_final.domain.wish.entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishRepository extends JpaRepository<Wish, Long> {

}