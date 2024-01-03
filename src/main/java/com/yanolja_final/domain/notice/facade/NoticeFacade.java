package com.yanolja_final.domain.notice.facade;


import com.yanolja_final.domain.notice.dto.request.RegisterNoticeRequest;
import com.yanolja_final.domain.notice.dto.response.RegisterNoticeResponse;
import com.yanolja_final.domain.notice.service.NoticeService;
import com.yanolja_final.global.util.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeFacade {

    private final NoticeService noticeService;

    public ResponseDTO<RegisterNoticeResponse> registerNotice(
        RegisterNoticeRequest registerNoticeRequest) {

        ResponseDTO<RegisterNoticeResponse> registerNoticeResponse = noticeService
            .registerNotice(registerNoticeRequest);
        return registerNoticeResponse;
    }
}
