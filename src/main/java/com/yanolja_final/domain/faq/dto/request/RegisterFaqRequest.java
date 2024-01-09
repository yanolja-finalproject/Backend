package com.yanolja_final.domain.faq.dto.request;

import com.yanolja_final.domain.faq.entity.Faq;
import jakarta.validation.constraints.NotNull;

public record RegisterFaqRequest(
    @NotNull
    String title,
    @NotNull
    String[] categories,
    @NotNull
    String[] content
) {
    public Faq toFaq() {
        String splitCategories = String.join(",", categories);
        String splitContent = String.join("\n", content);

        return Faq.builder()
            .title(title)
            .categories(splitCategories)
            .content(splitContent)
            .build();
    }
}
