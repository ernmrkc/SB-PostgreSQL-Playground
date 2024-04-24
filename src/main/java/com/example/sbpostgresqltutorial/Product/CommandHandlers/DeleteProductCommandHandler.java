package com.example.sbpostgresqltutorial.Product.CommandHandlers;

import com.example.sbpostgresqltutorial.Command;
import com.example.sbpostgresqltutorial.Exceptions.ProductNotFoundException;
import com.example.sbpostgresqltutorial.Product.Model.Product;
import com.example.sbpostgresqltutorial.Product.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteProductCommandHandler implements Command<Integer, ResponseEntity<Void>> {

    private final ProductRepository productRepository;

    public DeleteProductCommandHandler(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<Void> execute(Integer id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException();
        }
        Product product = productOptional.get();
        productRepository.delete(product);
        return ResponseEntity.ok().build();
    }
}
