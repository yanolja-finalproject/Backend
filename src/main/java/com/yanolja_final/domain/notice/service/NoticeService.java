package com.yanolja_final.domain.notice.service;

import com.yanolja_final.domain.notice.dto.request.RegisterNoticeRequest;
import com.yanolja_final.domain.notice.dto.response.RegisterNoticeResponse;
import com.yanolja_final.domain.notice.entity.Notice;
import com.yanolja_final.domain.notice.repository.NoticeRepository;
import com.yanolja_final.global.util.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public ResponseDTO<RegisterNoticeResponse> registerNotice(
        RegisterNoticeRequest registerNoticeRequest) {

        Notice notice = registerNoticeRequest.toEntity();
        Notice newNotice = noticeRepository.save(notice);

        return ResponseDTO.okWithData(RegisterNoticeResponse.from(newNotice));

    }
}
