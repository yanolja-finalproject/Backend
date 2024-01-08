package com.yanolja_final.domain.order.repository;

import com.yanolja_final.domain.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
