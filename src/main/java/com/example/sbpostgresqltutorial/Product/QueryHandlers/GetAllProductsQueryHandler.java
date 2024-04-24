package com.example.sbpostgresqltutorial.Product.QueryHandlers;

import com.example.sbpostgresqltutorial.Product.Model.ProductDTO;
import com.example.sbpostgresqltutorial.Product.ProductRepository;
import com.example.sbpostgresqltutorial.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllProductsQueryHandler implements Query<Void, List<ProductDTO>> {

    private final ProductRepository productRepository;

    public GetAllProductsQueryHandler(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<List<ProductDTO>> execute(Void input) {
        List<ProductDTO> productDTOs = productRepository.getAllProductDTOs();
        return ResponseEntity.ok(productDTOs);
    }
}
