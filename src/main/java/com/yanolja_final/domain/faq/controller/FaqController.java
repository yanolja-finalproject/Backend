package com.yanolja_final.domain.faq.controller;

import com.yanolja_final.domain.faq.dto.request.RegisterFaqRequest;
import com.yanolja_final.domain.faq.dto.response.FaqListResponse;
import com.yanolja_final.domain.faq.dto.response.FaqResponse;
import com.yanolja_final.domain.faq.facade.FaqFacade;
import com.yanolja_final.global.util.ResponseDTO;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/faq")
@RequiredArgsConstructor
public class FaqController {

    private final FaqFacade faqFacade;

    @PostMapping
    public ResponseEntity<ResponseDTO<FaqResponse>> createFaq(
        @Valid @RequestBody RegisterFaqRequest request
    ) {
        ResponseDTO<FaqResponse> response = faqFacade.registerFaq(request);
        return ResponseEntity.status(HttpStatus.valueOf(response.getCode())).body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<FaqListResponse>>> getFaqList() {
        ResponseDTO<List<FaqListResponse>> response = faqFacade.getFaqList();
        return ResponseEntity.status(HttpStatus.valueOf(response.getCode())).body(response);
    }

    @GetMapping("/{faqId}")
    public ResponseEntity<ResponseDTO<FaqResponse>> getSpecificFaq(
        @PathVariable Long faqId
    ) {
        ResponseDTO<FaqResponse> response = faqFacade.getSpecificFaq(faqId);
        return ResponseEntity.status(HttpStatus.valueOf(response.getCode())).body(response);
    }
}
