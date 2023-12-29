package com.yanolja_final.domain.user.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.yanolja_final.domain.user.dto.request.CreateUserRequest;
import com.yanolja_final.domain.user.dto.response.CreateUserResponse;
import com.yanolja_final.domain.user.facade.UserFacade;
import com.yanolja_final.global.util.ResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @PostMapping
    public ResponseEntity<ResponseDTO<CreateUserResponse>> signup(
        @Valid @RequestBody CreateUserRequest createUserRequest) {
        ResponseDTO<CreateUserResponse> response = userFacade.signUp(createUserRequest);
        return ResponseEntity.status(HttpStatus.valueOf(response.getCode())).body(response);
    }

    @DeleteMapping
    public ResponseEntity<ResponseDTO<Void>> deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = Long.parseLong(authentication.getName());
        userFacade.deleteUser(userId);
        return ResponseEntity.ok(ResponseDTO.ok());
    }
}
