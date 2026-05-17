package com.shamim.eticket.entities.base;

import com.shamim.eticket.global.interfaces.SelfAuditable;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class Auditable {

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void onPrePersist() {
        if ("SELF_REGISTRATION".equals(this.createdBy) || this.createdBy == null) {
            // Check if this specific entity knows how to audit itself
            if (this instanceof SelfAuditable selfAuditableEntity) {
                String selfIdentifier = selfAuditableEntity.getSelfAuditorIdentifier();
                if (selfIdentifier != null) {
                    this.createdBy = selfIdentifier;
                    this.updatedBy = selfIdentifier;
                    return;
                }
            }
            // Fallback for any other anonymous/system creations (like background tasks)
            this.createdBy = "SYSTEM";
            this.updatedBy = "SYSTEM";
        }
    }
}