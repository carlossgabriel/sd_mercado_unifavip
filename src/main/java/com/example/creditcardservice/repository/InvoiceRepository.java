package com.example.creditcardservice.repository;

import com.example.creditcardservice.domain.CreditCard;
import com.example.creditcardservice.domain.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    public Optional<Invoice> findByIdAndDeletedIsFalse(Long id);

    public List<Invoice> findAllByCreditCard(CreditCard creditCard);

}
