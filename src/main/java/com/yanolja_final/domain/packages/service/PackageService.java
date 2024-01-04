package com.yanolja_final.domain.packages.service;

import com.yanolja_final.domain.packages.entity.Package;
import com.yanolja_final.domain.packages.exception.PackageNotFoundException;
import com.yanolja_final.domain.packages.repository.PackageRepository;
import lombok.RequiredArgsConstructor;
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
}
