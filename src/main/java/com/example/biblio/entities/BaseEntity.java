package com.example.biblio.entities;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)

@Getter
@Setter
public abstract class BaseEntity {
    @CreatedDate
    @Setter(AccessLevel.NONE)
    protected LocalDateTime createdAt;
    @LastModifiedDate
    protected LocalDateTime updatedAt;

}
