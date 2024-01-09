package com.yanolja_final.domain.faq.facade;

import com.yanolja_final.domain.faq.dto.request.RegisterFaqRequest;
import com.yanolja_final.domain.faq.dto.response.FaqListResponse;
import com.yanolja_final.domain.faq.dto.response.FaqResponse;
import com.yanolja_final.domain.faq.service.FaqService;
import com.yanolja_final.global.util.ResponseDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FaqFacade {

    private final FaqService faqService;

    public ResponseDTO<FaqResponse> registerFaq(RegisterFaqRequest request) {
        ResponseDTO<FaqResponse> response = faqService.registerFaq(request);
        return response;
    }

    public ResponseDTO<List<FaqListResponse>> getFaqList() {
        ResponseDTO<List<FaqListResponse>> response = faqService.getFaqList();
        return response;
    }

    public ResponseDTO<FaqResponse> getSpecificFaq(Long faqId) {
        ResponseDTO<FaqResponse> response = faqService.getSpecificFaq(faqId);
        return response;
    }
}
