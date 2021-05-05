package com.example.creditcardservice.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "invoice")
@DynamicUpdate
@Data
public class Invoice extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private String creditCardToken;

    @ManyToOne
    @JoinColumn(name = "credit_card_id", nullable = false)
    private CreditCard creditCard;

    @Column(nullable = false)
    private Double value;

    @Column(nullable = false)
    private Integer installments;

    @Column(nullable = false)
    private String establishment;

    @Type(type = "org.hibernate.type.LocalDateTimeType")
    @Column(nullable = false)
    private LocalDateTime date;

    @Type(type = "org.hibernate.type.LocalDateType")
    @Column(name = "lastI_intallment_date", nullable = false)
    private LocalDate lastIntallmentDate;

}
