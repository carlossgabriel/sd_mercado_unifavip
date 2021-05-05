package com.example.creditcardservice.repository;

import com.example.creditcardservice.domain.Product;
import com.example.creditcardservice.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    public Optional<Product> findByIdAndDeletedIsFalse(Long id);

}
