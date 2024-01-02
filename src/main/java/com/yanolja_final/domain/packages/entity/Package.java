package com.yanolja_final.domain.packages.entity;

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
import java.sql.Time;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Package extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Time departureTime;

    @Column(nullable = false)
    private Time endTime;

    @Column(length = 30, nullable = false)
    private String nationName;

    @Column(length = 30, nullable = false)
    private String continentName;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 30, nullable = false)
    private String transportation;

    @Column(length = 100, nullable = false)
    private String info;

    @Column(name = "intro_image_url", length = 500, nullable = false)
    private String introImage;

    @Column(nullable = false)
    private Integer lodgeDays;

    @Column(nullable = false)
    private Integer tripDays;

    @Column(name = "inclusion_list_json", columnDefinition = "TEXT", nullable = false)
    private String inclusionList;

    @Column(name = "exclusion_list_json", columnDefinition = "TEXT", nullable = false)
    private String exclusionList;

    @Column(nullable = false)
    private Integer viewedCount;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "aPackage", cascade = CascadeType.REMOVE)
    private List<PackageAvailableDate> availableDates;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "aPackage", cascade = CascadeType.REMOVE)
    private List<PackageImage> images;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "package_hashtag",
        joinColumns = {@JoinColumn(name = "package_id", referencedColumnName = "id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))},
        inverseJoinColumns = {@JoinColumn(name = "hashtag_id", referencedColumnName = "id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))}
    )
    private Set<Hashtag> hashtags;
}
