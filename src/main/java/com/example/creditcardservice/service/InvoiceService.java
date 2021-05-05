package com.example.creditcardservice.service;

import com.example.creditcardservice.domain.CreditCard;
import com.example.creditcardservice.domain.Invoice;
import com.example.creditcardservice.exceptions.BusinessException;
import com.example.creditcardservice.repository.CreditCardRepository;
import com.example.creditcardservice.repository.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InvoiceService {

    private InvoiceRepository repository;

    private CreditCardRepository creditCardRepository;

    public Invoice findById(Long id){
        return repository.findByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new BusinessException("Fatura não encontrada"));
    }

    public Invoice create(Invoice entity){
        CreditCard creditCard =
                creditCardRepository
                        .findByTokenAndDeletedIsFalse(entity.getCreditCardToken())
                        .orElseThrow(() -> new BusinessException("Token inválido"));
        LocalDateTime tokenExpirationTime = creditCard.getTokenExpirationTime();
        if(LocalDateTime.now().isAfter(tokenExpirationTime)){
            creditCard.setToken(null);
            creditCard.setTokenExpirationTime(null);
            creditCardRepository.save(creditCard);
            Optional.ofNullable(creditCard.getTokenExpirationTime()).orElseThrow(() -> new BusinessException("Token inválido"));
        }
        List<Invoice> invoices = repository.findAllByCreditCard(creditCard);
        Double totalInvoices = 0d;
        if(!invoices.isEmpty()){
            totalInvoices = invoices
                    .stream()
                    .map(invoice -> invoice.getValue())
                    .reduce((accumulator, current) -> accumulator + current )
                    .get();
        }
        if(totalInvoices + entity.getValue() > creditCard.getLimit()){
            Optional.of(null).orElseThrow(() -> new BusinessException("Limite do cartão ultrapassado."));
        }

        entity.setCreditCard(creditCard);
        entity.setDate(LocalDateTime.now());
        entity.setLastIntallmentDate(LocalDate.now().plusMonths(entity.getInstallments()));
        creditCard.setToken(null);
        creditCard.setTokenExpirationTime(null);
        creditCardRepository.save(creditCard);
        return repository.save(entity);
    }

}
