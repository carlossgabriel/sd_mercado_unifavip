package com.example.creditcardservice.resource.invoice.dto;

import com.example.creditcardservice.domain.CreditCard;
import com.example.creditcardservice.domain.Invoice;
import com.example.creditcardservice.resource.creditcard.dto.CreditCardCreateOrUpdateDTO;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Optional;

@Data
public class InvoiceCreateDTO {

    @NotEmpty(message = "Informe o token de segurança")
    private String creditCardToken;

    @NotNull(message = "Informe o valor da compra")
    private Double value;

    @NotNull(message = "Informe o número de parcelas")
    private Integer installments;

    @NotNull(message = "Informe o nome do estabelecimento")
    private String establishment;

    public static Invoice to(InvoiceCreateDTO dto){
        if(Objects.isNull(dto)){
            return null;
        }

        Invoice entity = new Invoice();

        Optional.ofNullable(dto.getCreditCardToken())
                .ifPresent(entity::setCreditCardToken);

        Optional.ofNullable(dto.getValue())
                .ifPresent(entity::setValue);

        Optional.ofNullable(dto.getInstallments())
                .ifPresent(entity::setInstallments);

        Optional.ofNullable(dto.getEstablishment())
                .ifPresent(entity::setEstablishment);

        return entity;
    }

}
