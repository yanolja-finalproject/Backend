package com.yanolja_final.domain.packages.service;

import com.yanolja_final.domain.packages.entity.Package;
import com.yanolja_final.domain.packages.entity.PackageDepartureOption;
import com.yanolja_final.domain.packages.exception.PackageDepartureOptionNotFoundException;
import com.yanolja_final.domain.packages.exception.PackageNotFoundException;
import com.yanolja_final.domain.packages.repository.PackageDepartureOptionRepository;
import com.yanolja_final.domain.packages.repository.PackageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PackageService {

    private final PackageRepository packageRepository;
    private final PackageDepartureOptionRepository packageDepartureOptionRepository;

    // Package
    public Package getPackageWithIncrementPurchasedCount(Long id){
        Package aPackage = findById(id);
        aPackage.plusPurchasedCount();
        return packageRepository.save(aPackage);
    }

    public Package findById(Long id) {
        return packageRepository.findById(id)
            .orElseThrow(() -> new PackageNotFoundException());
    }

    // PackageDepartureOption
    public void updateCurrentPeopleWithOrder(Long id, int orderTotalPeople){
        PackageDepartureOption packageDepartureOption = findByDepartureOptionId(id);
        packageDepartureOption.incrementCurrentReservationCount(orderTotalPeople);
        packageDepartureOptionRepository.save(packageDepartureOption);
    }

    public PackageDepartureOption findByDepartureOptionId(Long id) {
        return packageDepartureOptionRepository.findById(id).orElseThrow(
            PackageDepartureOptionNotFoundException::new);
    }
}
