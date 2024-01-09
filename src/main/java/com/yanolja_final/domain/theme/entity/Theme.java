package com.yanolja_final.domain.theme.entity;

import com.yanolja_final.domain.packages.entity.Hashtag;
import com.yanolja_final.global.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Theme extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String description;

    @Column(length = 500, nullable = false)
    private String imageUrl;

    @OneToOne
    @JoinColumn(name = "hashtag_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    Hashtag hashtag;
}
