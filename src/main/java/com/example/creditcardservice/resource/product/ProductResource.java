package com.example.creditcardservice.resource.product;

import com.example.creditcardservice.domain.Product;
import com.example.creditcardservice.resource.creditcard.dto.CreditCardDTO;
import com.example.creditcardservice.resource.product.dto.ProductCreateOrUpdateDTO;
import com.example.creditcardservice.resource.product.dto.ProductDTO;
import com.example.creditcardservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "products")
@AllArgsConstructor
public class ProductResource {

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> save(@RequestBody Product product){
        productService.save(product);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Product>> findAll(){
        return ResponseEntity.ok().body(productService.findAll());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> update(
            @Valid @RequestBody ProductCreateOrUpdateDTO dto,
            @PathVariable Long id){
        dto.setId(id);
        ProductDTO productDTO = Optional.of(dto)
                .map(ProductCreateOrUpdateDTO::to)
                .map(productService::update)
                .map(ProductDTO::from).get();
        return ResponseEntity.ok().body(productDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> delete(@PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}