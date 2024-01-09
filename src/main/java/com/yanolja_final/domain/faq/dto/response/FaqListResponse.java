package com.yanolja_final.domain.faq.dto.response;

import com.yanolja_final.domain.faq.entity.Faq;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public record FaqListResponse(
    Long faqId,
    String title,
    String createdAt,
    String[] categories
) {
    public static FaqListResponse fromFaq(Faq faq) {
        String[] splitCategories = faq.getCategories().split(",");
        splitCategories = Arrays.stream(splitCategories)
            .map(String::trim)
            .toArray(String[]::new);

        return new FaqListResponse(
            faq.getId(),
            faq.getTitle(),
            faq.getFormattedDate(),
            splitCategories
        );
    }

    public static List<FaqListResponse> fromFaqs(List<Faq> faqs) {
        return faqs.stream()
            .map(FaqListResponse::fromFaq)
            .collect(Collectors.toList());
    }
}

