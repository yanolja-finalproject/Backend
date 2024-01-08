package com.yanolja_final.domain.theme.service;

import com.yanolja_final.domain.theme.entity.Theme;
import com.yanolja_final.domain.theme.repository.ThemeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ThemeService {

    private final ThemeRepository themeRepository;

    public List<Theme> getAllThemes() {
        return themeRepository.findAll();
    }
}
