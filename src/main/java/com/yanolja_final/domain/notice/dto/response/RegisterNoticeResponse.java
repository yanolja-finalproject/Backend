package com.yanolja_final.domain.notice.dto.response;

import com.yanolja_final.domain.notice.entity.Notice;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
            getFormattedDate(notice.getCreatedAt()),
            splitContent
        );
    }

    public static String getFormattedDate(LocalDateTime createdAt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return createdAt.format(formatter);
    }
}

