package com.example.sbpostgresqltutorial.Product.QueryHandlers;

import com.example.sbpostgresqltutorial.Product.Model.Product;
import com.example.sbpostgresqltutorial.Product.Model.ProductSearchRequest;
import com.example.sbpostgresqltutorial.Product.ProductRepository;
import com.example.sbpostgresqltutorial.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetProductsByNameOrDescriptionContainingQueryHandler implements Query<ProductSearchRequest, List<Product>> {

    private final ProductRepository productRepository;
    public GetProductsByNameOrDescriptionContainingQueryHandler(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<List<Product>> execute(ProductSearchRequest productSearchRequest) {
        return ResponseEntity.ok().body(productRepository.findByNameOrDescriptionContaining(productSearchRequest.getName(), productSearchRequest.getDescription()));
    }
}
