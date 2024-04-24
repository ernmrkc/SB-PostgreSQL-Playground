package com.example.sbpostgresqltutorial.Product.QueryHandlers;

import com.example.sbpostgresqltutorial.Product.Model.Product;
import com.example.sbpostgresqltutorial.Product.ProductRepository;
import com.example.sbpostgresqltutorial.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetProductByNameQueryHandler implements Query<String, Product> {

    private final ProductRepository productRepository;
    public GetProductByNameQueryHandler(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<Product> execute(String name) {
        return ResponseEntity.ok().body(productRepository.findByName(name));
    }
}
