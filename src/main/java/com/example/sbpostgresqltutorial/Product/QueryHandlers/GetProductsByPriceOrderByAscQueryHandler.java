package com.example.sbpostgresqltutorial.Product.QueryHandlers;

import com.example.sbpostgresqltutorial.Product.Model.Product;
import com.example.sbpostgresqltutorial.Product.ProductRepository;
import com.example.sbpostgresqltutorial.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetProductsByPriceOrderByAscQueryHandler implements Query<Double, List<Product>> {
    private final ProductRepository productRepository;
    public GetProductsByPriceOrderByAscQueryHandler(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<List<Product>> execute(Double maxPrice) {
        return ResponseEntity.ok().body(productRepository.findByPriceLessThanOrderByPriceAsc(maxPrice));
    }
}
