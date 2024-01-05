package com.yanolja_final.global.util;

import java.util.HashMap;
import java.util.Map;
import org.springframework.data.domain.Page;

public class PaginationUtils {

    public static Map<String, Object> createPageResponse(Page<?> page) {
        Map<String, Object> response = new HashMap<>();
        response.put("data", page.getContent());
        response.put("page", Map.of(
            "currentPage", page.getNumber() + 1,
            "totalPages", page.getTotalPages(),
            "currentElements", page.getNumberOfElements(),
            "totalElements", page.getTotalElements()
        ));
        return response;
    }
}
