package com.yanolja_final.domain.wish.controller;

import com.yanolja_final.domain.wish.dto.request.WishRequest;
import com.yanolja_final.domain.wish.dto.response.WishListResponse;
import com.yanolja_final.domain.wish.entity.Wish;
import com.yanolja_final.domain.wish.facade.WishFacade;
import com.yanolja_final.global.config.argumentresolver.LoginedUserId;
import com.yanolja_final.global.util.ResponseDTO;

import java.util.HashMap;
import java.util.Map;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/wishes")
@RequiredArgsConstructor
public class WishController {

    private final WishFacade wishFacade;

    @PostMapping
    public ResponseEntity<ResponseDTO> createWish(@RequestBody WishRequest wishRequest,
        @LoginedUserId Long userId) {
        Wish wish = wishFacade.createWish(wishRequest.packageId(), userId);
        return ResponseEntity.ok(ResponseDTO.ok());
    }

    @DeleteMapping("/{wishId}")
    public ResponseEntity<ResponseDTO> deleteWish(@PathVariable Long wishId,
        @LoginedUserId Long userId) {
        wishFacade.deleteWish(wishId, userId);
        return ResponseEntity.ok(ResponseDTO.ok());
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<Map<String, Object>>> getUserWishes(
        @LoginedUserId Long userId,
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "5") int pageSize) {

        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<WishListResponse> wishesPage = wishFacade.getUserWishes(userId, pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("data", wishesPage.getContent());
        response.put("page", Map.of(
            "currentPage", wishesPage.getNumber() + 1,
            "totalPage", wishesPage.getTotalPages(),
            "currentElements", wishesPage.getNumberOfElements(),
            "totalElements", wishesPage.getTotalElements()
        ));

        return ResponseEntity.ok(ResponseDTO.okWithData(response));
    }
}
