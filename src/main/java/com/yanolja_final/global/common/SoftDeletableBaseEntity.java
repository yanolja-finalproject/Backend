package com.yanolja_final.global.common;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class SoftDeletableBaseEntity extends BaseEntity {

    @Column(insertable = false)
    protected LocalDateTime deletedAt;

    protected void delete(LocalDateTime currentTime) {
        if (deletedAt == null) {
            deletedAt = currentTime;
        }
    }

    public boolean isDeleted() {
        return deletedAt != null;
    }

    protected void restore() {
        deletedAt = null;
    }
}
