package com.yanolja_final.domain.packages.repository;

import com.yanolja_final.domain.packages.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<Package, Long> {

}
