package com.example.sbpostgresqltutorial.Product;

import com.example.sbpostgresqltutorial.Product.Model.Product;
import com.example.sbpostgresqltutorial.Product.Model.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // @Query write out own SQL statement
    @Query("SELECT new com.example.sbpostgresqltutorial.Product.Model.ProductDTO(p.name, p.description, p.price) FROM Product p")
    List<ProductDTO> getAllProductDTOs();
    @Query("SELECT p FROM Product p WHERE p.price < :maxPrice")
    List<ProductDTO> findProductWithPriceLessThan(@Param("maxPrice") double maxPrice);

    // Spring Data JPA to have Spring generate it for us
    Product findByName(String name);

    List<Product> findByDescriptionContainingIgnoreCase(String description);

    List<Product> findByNameOrDescriptionContaining(String name, String description);

    List<Product> findByPriceLessThanOrderByPriceAsc(Double maxPrice);
}
