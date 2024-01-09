package com.yanolja_final.domain.faq.dto.request;

import com.yanolja_final.domain.faq.entity.Faq;
import jakarta.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.stream.Collectors;

public record RegisterFaqRequest(
    @NotNull
    String title,
    @NotNull
    String[] categories,
    @NotNull
    String[] content
) {
    public Faq toEntity() {
        String splitCategories = Arrays.stream(categories)
            .map(String::trim)
            .collect(Collectors.joining(","));

        String splitContent = Arrays.stream(content)
            .map(String::trim)
            .collect(Collectors.joining("\n"));

        return Faq.builder()
            .title(title)
            .categories(splitCategories)
            .content(splitContent)
            .build();
    }
}
