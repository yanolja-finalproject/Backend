package com.yanolja_final.domain.notice.dto.request;

import com.yanolja_final.domain.notice.entity.Notice;
import jakarta.validation.constraints.NotNull;

public record RegisterNoticeRequest(
    @NotNull
    String title,
    @NotNull
    String[] content
) {

    public Notice toEntity() {

        String splitContent = String.join("\n", content);

        return Notice.builder()
            .title(title)
            .content(splitContent)
            .build();
    }
}


