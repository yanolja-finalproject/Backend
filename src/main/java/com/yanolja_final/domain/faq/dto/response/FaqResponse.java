package com.yanolja_final.domain.faq.dto.response;

import com.yanolja_final.domain.faq.entity.Faq;

public record FaqResponse(
    Long faqId,
    String title,
    String createdAt,
    String[] categories,
    String[] content
) {
    public static FaqResponse fromFaq(Faq faq) {
        String[] splitCategories = faq.getCategories().split(",");
        String[] splitContent = faq.getContent().split("\n");

        return new FaqResponse(
            faq.getId(),
            faq.getTitle(),
            faq.getFormattedDate(),
            splitCategories,
            splitContent
        );
    }
}
