package com.example.creditcardservice.service;

import com.example.creditcardservice.domain.CreditCard;
import com.example.creditcardservice.exceptions.BusinessException;
import com.example.creditcardservice.repository.CreditCardRepository;
import com.example.creditcardservice.utils.HashUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CreditCardService {

    private CreditCardRepository repository;

    public CreditCard findById(Long id){
        return repository.findByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new BusinessException("Cartão não encontrado"));
    }

    public CreditCard create(CreditCard entity){
        return repository.save(entity);
    }

    public CreditCard update(CreditCard entity){
        return repository.save(entity);
    }

    public void delete(Long id){
        CreditCard creditCard = findById(id);
        creditCard.setDeleted(true);
        repository.save(creditCard);
    }

    public String requestToken(CreditCard entity){
        entity = repository.findByNumberAndDueDateAndCvnAndDeletedIsFalse(
                entity.getNumber(),
                entity.getDueDate(),
                entity.getCvn())
                .orElseThrow(() -> new BusinessException("Cartão não é válido"));
        String token = HashUtils.generateSha256(entity.getNumber());
        entity.setToken(token);
        entity.setTokenExpirationTime(LocalDateTime.now().plusMinutes(30));
        repository.save(entity);
        return token;
    }

}
