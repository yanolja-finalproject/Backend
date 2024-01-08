package com.yanolja_final.domain.theme.facade;

import com.yanolja_final.domain.packages.entity.Package;
import com.yanolja_final.domain.packages.service.PackageService;
import com.yanolja_final.domain.theme.dto.response.PackageSummary;
import com.yanolja_final.domain.theme.dto.response.ThemePackageResponse;
import com.yanolja_final.domain.theme.dto.response.ThemeResponse;
import com.yanolja_final.domain.theme.entity.Theme;
import com.yanolja_final.domain.theme.service.ThemeService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ThemeFacade {

    private final ThemeService themeService;
    private final PackageService packageService;

    public List<ThemeResponse> getAllThemes() {
        return themeService.getAllThemes().stream()
            .map(ThemeResponse::fromTheme)
            .collect(Collectors.toList());
    }

    public Page<ThemePackageResponse> getThemePackages(Long themeId, String sortBy,
        Pageable pageable) {
        Theme theme = themeService.getThemeById(themeId);
        Page<Package> packagesPage = packageService.getPackagesByTheme(theme.getHashtag(), sortBy,
            pageable);

        return packagesPage.map(
            packageEntity -> ThemePackageResponse.fromThemeAndPackages(theme, packagesPage));
    }
}
