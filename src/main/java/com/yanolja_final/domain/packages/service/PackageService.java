package com.yanolja_final.domain.packages.service;

import com.yanolja_final.domain.packages.entity.Hashtag;
import com.yanolja_final.domain.packages.entity.Package;
import com.yanolja_final.domain.packages.exception.PackageNotFoundException;
import com.yanolja_final.domain.packages.repository.PackageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PackageService {

    private final PackageRepository packageRepository;

    public Package findById(Long id) {
        return packageRepository.findById(id)
            .orElseThrow(() -> new PackageNotFoundException());
    }

    public Page<Package> getPackagesByTheme(Hashtag hashtag, String sortBy, Pageable pageable) {
        Sort sort = Sort.by("departureTime").ascending();
        if ("price_desc".equals(sortBy)) {
            sort = Sort.by("price").descending();
        } else if ("price_asc".equals(sortBy)) {
            sort = Sort.by("price").ascending();
        }
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        return packageRepository.findByHashtagsContains(hashtag, pageable);
    }
}
