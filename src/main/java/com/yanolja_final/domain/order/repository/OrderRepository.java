package com.yanolja_final.domain.order.repository;

import com.yanolja_final.domain.order.entity.Order;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT MAX(o.createdAt) FROM Order o")
    Optional<LocalDateTime> findLatestCreatedAt();
}