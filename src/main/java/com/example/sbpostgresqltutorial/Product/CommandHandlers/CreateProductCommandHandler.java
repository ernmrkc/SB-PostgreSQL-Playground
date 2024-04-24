package com.example.sbpostgresqltutorial.Product.CommandHandlers;

import com.example.sbpostgresqltutorial.Command;
import com.example.sbpostgresqltutorial.Exceptions.ProductNotValidException;
import com.example.sbpostgresqltutorial.Product.Model.Product;
import com.example.sbpostgresqltutorial.Product.ProductRepository;
import io.micrometer.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateProductCommandHandler implements Command<Product, ResponseEntity<Void>> {

    private static final Logger logger = LoggerFactory.getLogger(CreateProductCommandHandler.class);
    private final ProductRepository productRepository;

    public CreateProductCommandHandler(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<Void> execute(Product product) {
        logger.info("Executing " + getClass() + " with " + product.toString());
        validateProduct(product);
        productRepository.save(product);
        return ResponseEntity.ok().build();
    }

    private void validateProduct(Product product){
        if(StringUtils.isBlank(product.getName())){
            logger.error("Product Not Valid Exception was thrown (invalid name)" + product);
            throw new ProductNotValidException("Product name cannot be empty");
        }

        if(StringUtils.isBlank(product.getDescription())){
            logger.error("Product Not Valid Exception was thrown (invalid description) " + product);
            throw new ProductNotValidException("Product description cannot be empty");
        }

        if(product.getPrice() <= 0.0){
            logger.error("Product Not Valid Exception was thrown (invalid price) " + product);
            throw new ProductNotValidException("Product price cannot be negative");
        }

        if(product.getQuantity() <= 0){
            logger.error("Product Not Valid Exception was thrown (invalid quantity) " + product);
            throw new ProductNotValidException("Product quantity cannot be negative");
        }
    }
}
