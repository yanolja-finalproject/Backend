package com.yanolja_final.domain.notice.dto.response;

import com.yanolja_final.domain.notice.entity.Notice;
import java.util.List;
import java.util.stream.Collectors;

public record NoticeListResponse(

    Long noticeId,
    String title,
    String createdAt

) {
    public static NoticeListResponse fromNotice(Notice notice) {
        return new NoticeListResponse(
            notice.getId(),
            notice.getTitle(),
            notice.getFormattedDate()
        );
    }

    public static List<NoticeListResponse> fromNotices(List<Notice> notices) {
        return notices.stream()
            .map(NoticeListResponse::fromNotice)
            .collect(Collectors.toList());

    }
}
