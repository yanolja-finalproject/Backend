package com.yanolja_final.domain.notice.service;

import com.yanolja_final.domain.notice.dto.request.RegisterNoticeRequest;
import com.yanolja_final.domain.notice.dto.response.NoticeListResponse;
import com.yanolja_final.domain.notice.dto.response.NoticeResponse;
import com.yanolja_final.domain.notice.entity.Notice;
import com.yanolja_final.domain.notice.exception.NoticeNotFoundException;
import com.yanolja_final.domain.notice.repository.NoticeRepository;
import com.yanolja_final.global.util.ResponseDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public ResponseDTO<NoticeResponse> registerNotice(RegisterNoticeRequest request) {
        Notice notice = request.toEntity();
        Notice newNotice = noticeRepository.save(notice);
        return ResponseDTO.okWithData(NoticeResponse.fromNotice(newNotice));
    }


    public ResponseDTO<List<NoticeListResponse>> getNoticeList() {
        List<Notice> notices = noticeRepository.findAll();
        List<NoticeListResponse> noticeListResponses = NoticeListResponse.fromNotices(notices);
        return ResponseDTO.okWithData(noticeListResponses);
    }


    public ResponseDTO<NoticeResponse> getSpecificNotice(Long noticeId) {
        Notice notice = noticeRepository.findById(noticeId)
            .orElseThrow(() -> new NoticeNotFoundException());
        NoticeResponse specificNoticeResponse = NoticeResponse.fromNotice(notice);
        return ResponseDTO.okWithData(specificNoticeResponse);
    }
}
