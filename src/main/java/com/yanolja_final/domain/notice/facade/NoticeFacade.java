package com.yanolja_final.domain.notice.facade;


import com.yanolja_final.domain.notice.dto.request.RegisterNoticeRequest;
import com.yanolja_final.domain.notice.dto.response.NoticeListResponse;
import com.yanolja_final.domain.notice.dto.response.NoticeResponse;
import com.yanolja_final.domain.notice.service.NoticeService;
import com.yanolja_final.global.util.ResponseDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeFacade {

    private final NoticeService noticeService;

    public ResponseDTO<NoticeResponse> registerNotice(RegisterNoticeRequest request) {
        ResponseDTO<NoticeResponse> response = noticeService.registerNotice(request);
        return response;
    }


    public ResponseDTO<List<NoticeListResponse>> getNoticeList() {
        ResponseDTO<List<NoticeListResponse>> response = noticeService.getNoticeList();
        return response;
    }


    public ResponseDTO<NoticeResponse> getSpecificNotice(Long noticeId) {
        ResponseDTO<NoticeResponse> response = noticeService.getSpecificNotice(noticeId);
        return response;
    }
}
