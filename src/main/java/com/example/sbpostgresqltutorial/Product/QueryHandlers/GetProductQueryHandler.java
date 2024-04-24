package com.example.sbpostgresqltutorial.Product.QueryHandlers;

import com.example.sbpostgresqltutorial.Exceptions.ProductNotFoundException;
import com.example.sbpostgresqltutorial.Product.Model.Product;
import com.example.sbpostgresqltutorial.Product.Model.ProductDTO;
import com.example.sbpostgresqltutorial.Product.ProductRepository;
import com.example.sbpostgresqltutorial.Query;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetProductQueryHandler implements Query<Integer, ProductDTO> {

    private final ProductRepository productRepository;

    public GetProductQueryHandler(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    @Cacheable("productCache")
    public ResponseEntity<ProductDTO> execute(Integer id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException();
        }
        return ResponseEntity.ok(new ProductDTO(productOptional.get()));
    }
}
