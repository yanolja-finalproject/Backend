package com.yanolja_final.domain.order.controller;

import com.yanolja_final.domain.order.controller.request.OrderCreateRequest;
import com.yanolja_final.domain.order.controller.response.OrderCreateResponse;
import com.yanolja_final.domain.order.facade.OrderFacade;
import com.yanolja_final.global.config.argumentresolver.LoginedUserId;
import com.yanolja_final.global.util.ResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/orders")
public class OrderController {

    private final OrderFacade orderFacade;

    @PostMapping
    public ResponseEntity<ResponseDTO<OrderCreateResponse>> create(
        @LoginedUserId Long userId,
        @Valid @RequestBody OrderCreateRequest request
    ) {
        return ResponseEntity.ok(ResponseDTO.okWithData(orderFacade.create(userId, request)));
    }

}
