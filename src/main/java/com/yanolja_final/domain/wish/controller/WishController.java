package com.yanolja_final.domain.wish.controller;

import com.yanolja_final.domain.wish.dto.request.WishRequest;
import com.yanolja_final.domain.wish.entity.Wish;
import com.yanolja_final.domain.wish.service.WishService;
import com.yanolja_final.global.config.argumentresolver.LoginedUserId;
import com.yanolja_final.global.util.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/wishes")
@RequiredArgsConstructor
public class WishController {

    private final WishService wishService;

    @PostMapping
    public ResponseEntity<ResponseDTO> createWish(@RequestBody WishRequest wishRequest, @LoginedUserId Long userId) {
        Wish wish = wishService.createWish(wishRequest.getPackageId(), userId);
        return ResponseEntity.ok(ResponseDTO.ok());
    }
}
