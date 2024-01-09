package com.yanolja_final.domain.order.controller.request;

public record OrderGuestInfoRequest(
    Integer adult,
    Integer infant,
    Integer baby
) {

}
