package com.yanolja_final.domain.theme.dto.response;

import com.yanolja_final.domain.theme.entity.Theme;
import com.yanolja_final.domain.packages.entity.Package;
import com.yanolja_final.domain.packages.entity.PackageDepartureOption;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;

public record ThemePackageResponse(
    Long themeId,
    String name,
    String description,
    Integer minPrice,
    String imageUrl,
    List<PackageSummary> packages

) {
    public static ThemePackageResponse fromThemeAndPackages(Theme theme, Page<Package> packagesPage) {
        Integer minPrice = packagesPage.getContent().stream()
            .flatMap(p -> p.getAvailableDates().stream())
            .mapToInt(PackageDepartureOption::getMinPrice)
            .min()
            .orElse(0);

        List<PackageSummary> packageSummaries = packagesPage.getContent().stream()
            .map(p -> new PackageSummary(
                p.getId(),
                p.getIntroImages().isEmpty() ? null : p.getIntroImages().get(0).getImageUrl(),
                p.getTransportation(),
                p.getTitle())
            )
            .collect(Collectors.toList());

        return new ThemePackageResponse(
            theme.getId(),
            theme.getName(),
            theme.getDescription(),
            minPrice,
            theme.getImageUrl(),
            packageSummaries
        );
    }
}
