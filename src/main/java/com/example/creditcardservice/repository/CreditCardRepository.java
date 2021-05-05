package com.example.creditcardservice.repository;

import com.example.creditcardservice.domain.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

    public Optional<CreditCard> findByIdAndDeletedIsFalse(Long id);

    public Optional<CreditCard> findByTokenAndDeletedIsFalse(String token);

    public Optional<CreditCard> findByNumberAndDueDateAndCvnAndDeletedIsFalse(String number, LocalDate dueDate, String cvn);

}
