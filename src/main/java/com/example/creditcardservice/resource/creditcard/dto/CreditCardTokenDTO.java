package com.example.creditcardservice.resource.creditcard.dto;

import lombok.Data;

@Data
public class CreditCardTokenDTO {

    private String token;

    public static CreditCardTokenDTO from(String token){
        CreditCardTokenDTO dto = new CreditCardTokenDTO();
        dto.setToken(token);
        return dto;
    }


}
