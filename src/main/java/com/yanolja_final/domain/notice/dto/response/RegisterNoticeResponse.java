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

        // "content" : ["",""] 향후 수정 가능성 있음
        String[] splitContent = notice.getContent().split("\n");

        if (splitContent.length == 0 || (splitContent.length == 1 && splitContent[0].isEmpty())) {
            splitContent = new String[]{" "," "};
        }

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

