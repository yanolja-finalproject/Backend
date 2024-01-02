package com.yanolja_final.domain.packages.entity;

import com.yanolja_final.domain.theme.entity.Theme;
import com.yanolja_final.global.common.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@NoArgsConstructor
@Getter
public class Hashtag extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(nullable = false)
    private int searchedCount = 0;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "hashtags")
    private Set<Package> packages;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "hashtag")
    private Theme theme;
}
