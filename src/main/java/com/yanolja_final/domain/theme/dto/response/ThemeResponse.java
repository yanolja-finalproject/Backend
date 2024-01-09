package com.yanolja_final.domain.theme.dto.response;

import com.yanolja_final.domain.theme.entity.Theme;

public record ThemeResponse(Long themeId, String name, String imageUrl) {
    public static ThemeResponse fromTheme(Theme theme) {
        return new ThemeResponse(theme.getId(), theme.getName(), theme.getImageUrl());
    }
}
