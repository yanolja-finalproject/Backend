package com.yanolja_final.domain.order.service;

import com.yanolja_final.domain.order.entity.Order;
import com.yanolja_final.domain.order.exception.OrderNotFoundException;
import com.yanolja_final.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    public Order findById(Long orderId) {
        return orderRepository.findById(orderId)
            .orElseThrow(() -> new OrderNotFoundException());
    }
}
