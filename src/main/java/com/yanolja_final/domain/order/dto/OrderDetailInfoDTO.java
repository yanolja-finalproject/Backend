package com.yanolja_final.domain.order.dto;

import com.yanolja_final.domain.order.controller.request.OrderGuestInfoRequest;

public record OrderDetailInfoDTO(
    String requestMessage,
    Boolean cancelFeeAgreement,
    OrderGuestInfoRequest numOfPeople
) {

}
