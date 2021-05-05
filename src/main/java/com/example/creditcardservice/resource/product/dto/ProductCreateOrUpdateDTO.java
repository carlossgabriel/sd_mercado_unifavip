package com.example.creditcardservice.resource.product.dto;

import com.example.creditcardservice.domain.Product;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Optional;

@Data
public class ProductCreateOrUpdateDTO {

    private Long id;

    @NotBlank(message = "Nome do produto não informado")
    @Length(min = 5, max = 25, message = "Nome do produto inválido")
    private String name;

    @NotNull(message = "Preço não informado")
    private Double price;

    @NotNull(message = "Quantidade em estoque não informada")
    private int storage;

    public static Product to(ProductCreateOrUpdateDTO dto){
        if (Objects.isNull(dto)){
            return  null;
        }

        Product entity = new Product();

        Optional.ofNullable(dto.getId()).ifPresent(entity::setId);
        Optional.ofNullable(dto.getName()).ifPresent(entity::setName);
        Optional.ofNullable(dto.getPrice()).ifPresent(entity::setPrice);
        Optional.ofNullable(dto.getStorage()).ifPresent(entity::setStorage);

        return entity;
    }

}
