package com.example.creditcardservice.resource.creditcard.dto;

import com.example.creditcardservice.domain.CreditCard;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@Data
public class CreditCardCreateOrUpdateDTO {

    private Long id;

    @NotBlank(message = "Número não informado")
    @Length(min = 16, max = 16, message = "Número inválido")
    private String number;

    @NotBlank(message = "Titular não informado")
    @Length(max = 60, message = "Nome do titular não pode ter mais que 60 caracteres")
    private String owner;

    @NotNull(message = "Validade não informada")
    @Future(message = "Data de validade não é válida")
    private LocalDate dueDate;

    @NotBlank(message = "Código de segurança não informado")
    @Length(min = 3, max = 3, message = "Código de segurança inválido")
    private String cvn;

    @NotNull(message = "Limite não informado")
    private Double limit;

    public static CreditCard to(CreditCardCreateOrUpdateDTO dto){
        if(Objects.isNull(dto)){
            return null;
        }

        CreditCard entity = new CreditCard();

        Optional.ofNullable(dto.getId())
                .ifPresent(entity::setId);

        Optional.ofNullable(dto.getNumber())
                .ifPresent(entity::setNumber);

        Optional.ofNullable(dto.getOwner())
                .ifPresent(entity::setOwner);

        Optional.ofNullable(dto.getDueDate())
                .ifPresent(entity::setDueDate);

        Optional.ofNullable(dto.getCvn())
                .ifPresent(entity::setCvn);

        Optional.ofNullable(dto.getLimit())
                .ifPresent(entity::setLimit);

        return entity;
    }

}
