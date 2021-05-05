package com.example.creditcardservice.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "credit_card")
@DynamicUpdate
@Data
public class CreditCard extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String number;

    @Column(nullable = false, length = 60)
    private String owner;

    @Type(type = "org.hibernate.type.LocalDateType")
    @Column(nullable = false, name = "due_date")
    private LocalDate dueDate;

    @Column(nullable = false, length = 3)
    private String cvn;

    @Column(name = "card_limit", nullable = false)
    private Double limit;

    @Column(length = 100)
    private String token;

    @Type(type = "org.hibernate.type.LocalDateTimeType")
    @Column(name = "token_expiration_time")
    private LocalDateTime tokenExpirationTime;

}
