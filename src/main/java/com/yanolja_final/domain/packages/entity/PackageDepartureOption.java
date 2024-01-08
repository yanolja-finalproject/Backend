package com.yanolja_final.domain.packages.entity;

import com.yanolja_final.global.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Arrays;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class PackageDepartureOption extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "package_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Package aPackage;

    @Column(nullable = false)
    private LocalDate departureDate;

    private Integer adultPrice;

    private Integer infantPrice;

    private Integer babyPrice;

    private Integer currentReservationCount;

    private Integer minReservationCount;

    private Integer maxReservationCount;

    public Integer getMinPrice() {
        return Arrays.asList(adultPrice, infantPrice, babyPrice).stream()
            .filter(price -> price != null && price > 0)
            .min(Integer::compare)
            .orElse(0);
    }
}
