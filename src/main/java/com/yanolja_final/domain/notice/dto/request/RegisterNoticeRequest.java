package com.yanolja_final.domain.notice.dto.request;

import com.yanolja_final.domain.notice.entity.Notice;

public record RegisterNoticeRequest(
    String title,
    String[] content
) {

    public Notice toEntity() {

        String joinContent = String.join("\n", content);

        return Notice.builder()
            .title(title)
            .content(joinContent)
            .build();
    }
}
