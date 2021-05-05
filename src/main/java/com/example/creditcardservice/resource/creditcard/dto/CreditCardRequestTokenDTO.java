package com.example.creditcardservice.resource.creditcard.dto;

import com.example.creditcardservice.domain.CreditCard;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@Data
public class CreditCardRequestTokenDTO {

    @NotBlank(message = "Número não informado")
    private String number;

    @NotNull(message = "Validade não informada")
    private LocalDate dueDate;

    @NotBlank(message = "Código de segurança não informado")
    private String cvn;

    public static CreditCard to(CreditCardRequestTokenDTO dto){
        if(Objects.isNull(dto)){
            return null;
        }

        CreditCard entity = new CreditCard();

        Optional.ofNullable(dto.getNumber())
                .ifPresent(entity::setNumber);

        Optional.ofNullable(dto.getDueDate())
                .ifPresent(entity::setDueDate);

        Optional.ofNullable(dto.getCvn())
                .ifPresent(entity::setCvn);

        return entity;
    }

}
