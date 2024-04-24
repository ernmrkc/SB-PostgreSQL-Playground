package ProductControllerTest;

import com.example.sbpostgresqltutorial.Exceptions.ProductNotFoundException;
import com.example.sbpostgresqltutorial.Exceptions.ProductNotValidException;
import com.example.sbpostgresqltutorial.Product.Model.Product;
import com.example.sbpostgresqltutorial.Product.Model.ProductDTO;
import com.example.sbpostgresqltutorial.Product.ProductRepository;
import com.example.sbpostgresqltutorial.Product.QueryHandlers.GetProductQueryHandler;
import com.example.sbpostgresqltutorial.SbPostgreSqlTutorialApplication;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = SbPostgreSqlTutorialApplication.class)
public class GetProductQueryHandlerTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private GetProductQueryHandler getProductQueryHandler;

    @Test
    public void getProductQueryHandler_validId_returnsProductDTO(){
        // Given
        Product product = new Product();
        product.setId(1);
        product.setName("iPhone 15 Pro Max");
        product.setDescription("Best Phone");
        product.setPrice(799.99);
        product.setQuantity(1000);
        ProductDTO expectedDTO = new ProductDTO(product);
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        // When
        ResponseEntity<ProductDTO> actualResponse = getProductQueryHandler.execute(product.getId());

        // Then
        assertEquals(expectedDTO, actualResponse.getBody());
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
    }

    @Test
    public void getProductQueryHandler_invalidId_throwsProductNotFoundException(){
        // Giver
        when(productRepository.findById(1)).thenReturn(Optional.empty());
        // When & Then
        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () ->
                getProductQueryHandler.execute(1));
        // Then
        assertEquals("Product Not Found", exception.getSimpleResponse().getMessage());
    }
}
