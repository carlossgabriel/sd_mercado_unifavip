package com.example.creditcardservice.resource.invoice;

import com.example.creditcardservice.resource.creditcard.dto.CreditCardCreateOrUpdateDTO;
import com.example.creditcardservice.resource.creditcard.dto.CreditCardDTO;
import com.example.creditcardservice.resource.invoice.dto.InvoiceCreateDTO;
import com.example.creditcardservice.service.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/invoice")
@AllArgsConstructor
public class InvoiceResource {

    private InvoiceService service;


    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody InvoiceCreateDTO dto){
        Optional.of(dto)
                .map(InvoiceCreateDTO::to)
                .map(service::create);
        return ResponseEntity.noContent().build();
    }
}
