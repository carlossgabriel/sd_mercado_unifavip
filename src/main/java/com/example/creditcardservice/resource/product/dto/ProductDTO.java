package com.example.creditcardservice.resource.product.dto;

import com.example.creditcardservice.domain.Product;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

@Data
public class ProductDTO implements Serializable {

    private String name;
    private Double price;
    private int storage;

    public static ProductDTO from(Product entity){
        if (Objects.isNull(entity)){
            return null;
        }
        ProductDTO dto = new ProductDTO();

        Optional.ofNullable(entity.getName()).ifPresent(dto::setName);
        Optional.ofNullable(entity.getPrice()).ifPresent(dto::setPrice);
        Optional.ofNullable(entity.getStorage()).ifPresent(dto::setStorage);

        return dto;
    }
}
