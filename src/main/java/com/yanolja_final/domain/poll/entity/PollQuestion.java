package com.yanolja_final.domain.poll.entity;

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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "poll_question")
@Entity
@NoArgsConstructor
@Getter
public class PollQuestion extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poll_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Poll poll;

    @Column(length = 100, nullable = false)
    private String content;

    @Column(name = "sequence", nullable = false)
    private Integer sequence;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pollQuestion", cascade = CascadeType.REMOVE)
    private List<PollAnswer> pollAnswer;
}
