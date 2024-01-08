package com.yanolja_final.domain.theme.facade;

import com.yanolja_final.domain.theme.dto.response.ThemeResponse;
import com.yanolja_final.domain.theme.service.ThemeService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ThemeFacade {

    private final ThemeService themeService;

    public List<ThemeResponse> getAllThemes() {
        return themeService.getAllThemes().stream()
            .map(ThemeResponse::fromTheme)
            .collect(Collectors.toList());
    }
}
