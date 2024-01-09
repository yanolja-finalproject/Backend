package com.yanolja_final.domain.order.controller.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yanolja_final.domain.order.dto.OrderDetailInfoDTO;
import com.yanolja_final.domain.order.entity.Order;
import com.yanolja_final.domain.packages.entity.Package;
import com.yanolja_final.domain.user.entity.User;

public record OrderCreateRequest(
    Long packageId,
    Long availableDateId,
    String requestMessage,
    Boolean cancelFeeAgreement,

    OrderGuestInfoRequest numOfPeople
) {

    public Order toEntity(User user, Package aPackage, String code) {
        return Order.builder()
            .user(user)
            .aPackage(aPackage)
            .availableDateId(availableDateId)
            .code(code)
            .detailInfo(createOrderDetailInfo())
            .build();
    }

    private String createOrderDetailInfo() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            OrderDetailInfoDTO
                details =
                new OrderDetailInfoDTO(requestMessage, cancelFeeAgreement, numOfPeople);

            return objectMapper.writeValueAsString(details);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
