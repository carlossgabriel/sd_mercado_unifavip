package com.example.creditcardservice.service;

import com.example.creditcardservice.domain.Product;
import com.example.creditcardservice.exceptions.BusinessException;
import com.example.creditcardservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public void save(Product product){
        product.setRegisterDate(new Date());
        repository.save(product);
    }

    public Product findById(Long id){
        return repository.findByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new BusinessException("Produto não encontrado"));
    }

    public List<Product> findAll(){
        return repository.findAll();
    }

    public Product update(Product entity){
        return repository.save(entity);
    }

    //TODO fix delete method in product
    public void delete(Long id){
        Product product = findById(id);
//        product.setDe
//        repository.save(product);
    }

}
