package com.example.sbpostgresqltutorial.Product.QueryHandlers;

import com.example.sbpostgresqltutorial.Product.Model.ProductDTO;
import com.example.sbpostgresqltutorial.Product.ProductRepository;
import com.example.sbpostgresqltutorial.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetProductsByPriceLessThanQueryHandler implements Query<Double, List<ProductDTO>> {
    private final ProductRepository productRepository;
    public GetProductsByPriceLessThanQueryHandler(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<List<ProductDTO>> execute(Double input) {
        return ResponseEntity.ok().body(productRepository.findProductWithPriceLessThan(input));
    }
}
