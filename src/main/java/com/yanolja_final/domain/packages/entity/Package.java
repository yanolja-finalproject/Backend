package com.yanolja_final.domain.packages.entity;

import com.yanolja_final.global.common.BaseEntity;
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
import jakarta.persistence.OneToOne;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Package extends BaseEntity {

    @Id
    private Long id;

    @Column(nullable = false)
    private LocalTime departureTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @OneToOne
    private Nation nation;

    @OneToOne
    private Continent continent;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 30, nullable = false)
    private String transportation;

    @Column(length = 100, nullable = false)
    private String info;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<PackageIntroImage> introImages;

    @Column(nullable = false)
    private Integer lodgeDays;

    @Column(nullable = false)
    private Integer tripDays;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String inclusionList;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String exclusionList;

    @Column(nullable = false)
    private Integer viewedCount = 0;

    @Column(nullable = false)
    private Integer purchasedCount = 0;

    @Column(nullable = false)
    private Integer shoppingCount = 0;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String schedules;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<PackageDepartureOption> availableDates;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<PackageImage> images;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "package_hashtag",
        joinColumns = {
            @JoinColumn(name = "package_id", referencedColumnName = "id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))},
        inverseJoinColumns = {
            @JoinColumn(name = "hashtag_id", referencedColumnName = "id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))}
    )
    private Set<Hashtag> hashtags;

    public String getNationName() {
        return this.nation.getName();
    }

    public void plusPurchasedCount(){
        this.purchasedCount++;
    }
}
