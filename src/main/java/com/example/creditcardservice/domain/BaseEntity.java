package com.example.creditcardservice.domain;

import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Audited
@EntityListeners({AuditingEntityListener.class})
@Data
public class BaseEntity {

    @CreatedBy
    @Column(name = "created_by", nullable = false, length = 60, updatable = false)
    private String createdBy = "system";

    @CreatedDate
    @Type(type = "org.hibernate.type.LocalDateTimeType")
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate = LocalDateTime.now();

    @LastModifiedBy
    @Column(name = "last_modified_by", length = 60)
    private String lastModifiedBy= "system";

    @LastModifiedDate
    @Type(type = "org.hibernate.type.LocalDateTimeType")
    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate= LocalDateTime.now();

    @Column(nullable = false)
    private boolean deleted = false;

}
