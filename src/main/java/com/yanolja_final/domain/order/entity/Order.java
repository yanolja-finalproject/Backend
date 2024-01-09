package com.yanolja_final.domain.order.entity;

import com.yanolja_final.domain.packages.entity.Package;
import com.yanolja_final.domain.review.entity.Review;
import com.yanolja_final.domain.user.entity.User;
import com.yanolja_final.global.common.SoftDeletableBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "orders")
@Entity
@NoArgsConstructor
@Getter
public class Order extends SoftDeletableBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "package_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Package aPackage;

    @OneToOne(mappedBy = "order", fetch = FetchType.LAZY)
    private Review review;

    @Column(nullable = false)
    private Long availableDateId;

    @Column(length = 100, nullable = false)
    private String code;

    @Column(name = "detail", columnDefinition = "TEXT", nullable = false)
    private String detailInfo;

    @Builder
    public Order(User user, Package aPackage, Review review, Long availableDateId, String code,
        String detailInfo) {
        this.user = user;
        this.aPackage = aPackage;
        this.review = review;
        this.availableDateId = availableDateId;
        this.code = code;
        this.detailInfo = detailInfo;
    }
}
