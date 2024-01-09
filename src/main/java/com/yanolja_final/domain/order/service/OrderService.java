package com.yanolja_final.domain.order.service;

import com.yanolja_final.domain.order.controller.request.OrderCreateRequest;
import com.yanolja_final.domain.order.controller.response.OrderCreateResponse;
import com.yanolja_final.domain.order.entity.Order;
import com.yanolja_final.domain.order.exception.OrderNotFoundException;
import com.yanolja_final.domain.order.repository.OrderRepository;
import com.yanolja_final.domain.packages.entity.Package;
import com.yanolja_final.domain.user.entity.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private static int dailyOrderCount = -1;

    @Transactional
    public OrderCreateResponse create(User user, Package aPackage,
        OrderCreateRequest request) {
        String code = generateDailyOrderCode();
        Order order = request.toEntity(user, aPackage, code);
        orderRepository.save(order);
        return OrderCreateResponse.fromEntities(order, user);
    }

    private String generateDailyOrderCode() {
        LocalDate today = LocalDate.now();
        LocalDateTime lastOrderDateTime = orderRepository.findTopCreatedAt();
        if (dailyOrderCount == -1 || !today.equals(lastOrderDateTime.toLocalDate())) {
            dailyOrderCount = 0;
        }

        String orderCode = String.format("%04d%02d%02d%05d",
            today.getYear(), today.getMonthValue(), today.getDayOfMonth(), ++dailyOrderCount);
        return orderCode;
    }

    public Order findById(Long orderId) {
        return orderRepository.findById(orderId)
            .orElseThrow(() -> new OrderNotFoundException());
    }
}
