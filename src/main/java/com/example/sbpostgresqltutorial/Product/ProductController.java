package com.example.sbpostgresqltutorial.Product;

import com.example.sbpostgresqltutorial.Product.CommandHandlers.CreateProductCommandHandler;
import com.example.sbpostgresqltutorial.Product.CommandHandlers.DeleteProductCommandHandler;
import com.example.sbpostgresqltutorial.Product.CommandHandlers.UpdateProductCommandHandler;
import com.example.sbpostgresqltutorial.Product.Model.Product;
import com.example.sbpostgresqltutorial.Product.Model.ProductDTO;
import com.example.sbpostgresqltutorial.Product.Model.ProductSearchRequest;
import com.example.sbpostgresqltutorial.Product.Model.UpdateProductCommand;
import com.example.sbpostgresqltutorial.Product.QueryHandlers.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    // Constructor Injection
    // private final ProductRepository productRepository1;
    private final GetAllProductsQueryHandler getAllProductsQueryHandler;
    private final GetProductQueryHandler getProductQueryHandler;
    private final CreateProductCommandHandler createProductCommandHandler;
    private final UpdateProductCommandHandler updateProductCommandHandler;
    private final DeleteProductCommandHandler deleteProductCommandHandler;
    private final GetProductsByPriceLessThanQueryHandler getProductsByPriceLessThanQueryHandler;
    private final GetProductByNameQueryHandler getProductByNameQueryHandler;
    private final GetProductsByDescriptionContainingQueryHandler getProductsByDescriptionContainingQueryHandler;
    private final GetProductsByNameOrDescriptionContainingQueryHandler getProductsByNameOrDescriptionContainingQueryHandler;
    private final GetProductsByPriceOrderByAscQueryHandler getProductsByPriceOrderByAscQueryHandler;

    public ProductController(//ProductRepository productRepository,
                             GetAllProductsQueryHandler getAllProductsQueryHandler,
                             GetProductQueryHandler getProductQueryHandler,
                             CreateProductCommandHandler createProductCommandHandler,
                             UpdateProductCommandHandler updateProductCommandHandler,
                             DeleteProductCommandHandler deleteProductCommandHandler,
                             GetProductsByPriceLessThanQueryHandler getProductsByPriceLessThanQueryHandler,
                             GetProductByNameQueryHandler getProductByNameQueryHandler,
                             GetProductsByDescriptionContainingQueryHandler getProductsByDescriptionContainingQueryHandler,
                             GetProductsByNameOrDescriptionContainingQueryHandler getProductsByNameOrDescriptionContainingQueryHandler,
                             GetProductsByPriceOrderByAscQueryHandler getProductsByPriceOrderByAscQueryHandler) {
        //this.productRepository = productRepository;
        this.getAllProductsQueryHandler = getAllProductsQueryHandler;
        this.getProductQueryHandler = getProductQueryHandler;
        this.createProductCommandHandler = createProductCommandHandler;
        this.updateProductCommandHandler = updateProductCommandHandler;
        this.deleteProductCommandHandler = deleteProductCommandHandler;
        this.getProductsByPriceLessThanQueryHandler = getProductsByPriceLessThanQueryHandler;
        this.getProductByNameQueryHandler = getProductByNameQueryHandler;
        this.getProductsByDescriptionContainingQueryHandler = getProductsByDescriptionContainingQueryHandler;
        this.getProductsByNameOrDescriptionContainingQueryHandler = getProductsByNameOrDescriptionContainingQueryHandler;
        this.getProductsByPriceOrderByAscQueryHandler = getProductsByPriceOrderByAscQueryHandler;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts(){
        return getAllProductsQueryHandler.execute(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Integer id){
        return getProductQueryHandler.execute(id);
    }

    @GetMapping("/search/{maxPrice}")
    public ResponseEntity<List<ProductDTO>> findProductsByPriceLessThan(@PathVariable double maxPrice){
        return getProductsByPriceLessThanQueryHandler.execute(maxPrice);
    }

    @GetMapping("/searchByName")
    public ResponseEntity<Product> findProductsByName(@RequestParam(value = "name") String name){
        return getProductByNameQueryHandler.execute(name);
    }

    @GetMapping("/searchByDescriptionContaining")
    public ResponseEntity<List<Product>> findProductsByDescriptionContainingIgnoreCase(@RequestParam(value = "description") String description){
        return getProductsByDescriptionContainingQueryHandler.execute(description);
    }

    @GetMapping("/searchByNameOrDescriptionContaining")
    public ResponseEntity<List<Product>> findProductsByNameOrDescriptionContaining(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "description", required = false) String description){
        ProductSearchRequest queryModel = new ProductSearchRequest(name, description);
        return getProductsByNameOrDescriptionContainingQueryHandler.execute(queryModel);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> findProductByPriceOrderByAsc(@RequestParam(value = "price") double maxPrice){
        return getProductsByPriceOrderByAscQueryHandler.execute(maxPrice);
    }

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody Product product){
        return createProductCommandHandler.execute(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Integer id, @RequestBody Product product){
        UpdateProductCommand command = new UpdateProductCommand(id, product);
        return updateProductCommandHandler.execute(command);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id){
        return deleteProductCommandHandler.execute(id);
    }
}
