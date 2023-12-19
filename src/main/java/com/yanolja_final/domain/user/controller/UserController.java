package com.yanolja_final.domain.user.controller;

import com.yanolja_final.domain.user.controller.request.SignUpRequest;
import com.yanolja_final.domain.user.controller.response.SignUpResponse;
import com.yanolja_final.domain.user.facade.UserFacade;
import com.yanolja_final.global.util.ResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ResponseDTO<SignUpResponse>> signUp(
        @RequestBody @Valid SignUpRequest signUpRequest
    ) {
        SignUpResponse signUpResponse = userFacade.signUp(signUpRequest);
        return ResponseEntity
            .ok(ResponseDTO.okWithData(signUpResponse));
    }
}
