package com.example.sbpostgresqltutorial.Product.CommandHandlers;

import com.example.sbpostgresqltutorial.Command;
import com.example.sbpostgresqltutorial.Exceptions.ProductNotFoundException;
import com.example.sbpostgresqltutorial.Product.Model.Product;
import com.example.sbpostgresqltutorial.Product.Model.ProductDTO;
import com.example.sbpostgresqltutorial.Product.Model.UpdateProductCommand;
import com.example.sbpostgresqltutorial.Product.ProductRepository;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateProductCommandHandler implements Command<UpdateProductCommand, ResponseEntity<ProductDTO>> {

    private final ProductRepository productRepository;

    public UpdateProductCommandHandler(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    @CachePut(value = "productCache", key="#command.getId()")
    public ResponseEntity<ProductDTO> execute(UpdateProductCommand command) {
        Optional<Product> productOptional = productRepository.findById(command.getId());
        if (productOptional.isEmpty()){
            throw new ProductNotFoundException();
        }

        Product product = command.getProduct();
        product.setId(command.getId());
        productRepository.save(product);
        return ResponseEntity.ok(new ProductDTO(product));
    }
}
