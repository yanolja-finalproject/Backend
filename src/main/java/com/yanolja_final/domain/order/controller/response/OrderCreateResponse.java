package com.yanolja_final.domain.order.controller.response;

import com.yanolja_final.domain.order.entity.Order;
import com.yanolja_final.domain.user.entity.User;

public record OrderCreateResponse(
    String orderCode,
    UserInfoResponse myInfo
) {
    public static OrderCreateResponse fromEntities(Order order, User user){
        return new OrderCreateResponse(
            order.getCode(),
            UserInfoResponse.fromEntity(user)
        );
    }
}
