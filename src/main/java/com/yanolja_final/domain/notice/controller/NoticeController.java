package com.yanolja_final.domain.notice.controller;


import com.yanolja_final.domain.notice.dto.request.RegisterNoticeRequest;
import com.yanolja_final.domain.notice.dto.response.RegisterNoticeResponse;
import com.yanolja_final.domain.notice.facade.NoticeFacade;
import com.yanolja_final.global.util.ResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/notices")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeFacade noticeFacade;

    @PostMapping
    public ResponseEntity<ResponseDTO<RegisterNoticeResponse>> createNotice(
        @Valid @RequestBody RegisterNoticeRequest registerNoticeRequest
    ) {
        ResponseDTO<RegisterNoticeResponse> response = noticeFacade.registerNotice(
            registerNoticeRequest);

        return ResponseEntity.status(HttpStatus.valueOf(response.getCode())).body(response);
    }
}
