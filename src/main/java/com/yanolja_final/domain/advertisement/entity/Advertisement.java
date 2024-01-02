package com.yanolja_final.domain.advertisement.entity;

import com.yanolja_final.domain.packages.entity.Package;
import com.yanolja_final.global.common.BaseTimeEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Advertisement extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "advertisement")
    private List<AdvertisementImage> images;

    @ManyToMany
    @JoinTable(
        name = "advertisement_package",
        joinColumns = {@JoinColumn(name = "ad_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "package_id", referencedColumnName = "id")}
    )
    private Set<Package> packages;

    @Builder
    private Advertisement(String name, String description, List<AdvertisementImage> images,
        Set<Package> packages) {
        this.name = name;
        this.description = description;
        this.images = images;
        this.packages = packages;
    }
}
