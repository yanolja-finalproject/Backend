package com.yanolja_final.domain.notice.dto.response;

import com.yanolja_final.domain.notice.entity.Notice;


public record RegisterNoticeResponse(
    Long noticeId,
    String title,
    String createdAt,
    String[] content

) {


    public static RegisterNoticeResponse from(Notice notice) {

        String[] splitContent = notice.getContent().split("\n");

        return new RegisterNoticeResponse(
            notice.getId(),
            notice.getTitle(),
            notice.getFormattedDate(),
            splitContent
        );
    }
}

