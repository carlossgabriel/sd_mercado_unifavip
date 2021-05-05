package com.example.creditcardservice.resource.creditcard.dto;

import com.example.creditcardservice.domain.CreditCard;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

@Data
public class CreditCardDTO implements Serializable {

    private String number;
    private String owner;

    public static CreditCardDTO from(CreditCard entity){
        if(Objects.isNull(entity)){
            return null;
        }
        CreditCardDTO dto = new CreditCardDTO();

        Optional.ofNullable(entity.getNumber())
                .ifPresent(dto::setNumber);

        Optional.ofNullable(entity.getOwner())
                .ifPresent(dto::setOwner);

        return dto;
    }



}
