package ProductControllerTest;

import com.example.sbpostgresqltutorial.Exceptions.ProductNotFoundException;
import com.example.sbpostgresqltutorial.Exceptions.ProductNotValidException;
import com.example.sbpostgresqltutorial.Product.CommandHandlers.CreateProductCommandHandler;
import com.example.sbpostgresqltutorial.Product.Model.Product;
import com.example.sbpostgresqltutorial.Product.ProductRepository;
import com.example.sbpostgresqltutorial.SbPostgreSqlTutorialApplication;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = SbPostgreSqlTutorialApplication.class)
public class CreateProductCommandHandlerTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private CreateProductCommandHandler createProductCommandHandler;

    // MethodName_StateUnderTest_ExpectedBehavior
    @Test
    public void createProductCommandHandler_validProduct_returnsSuccess(){
        Product product = new Product();
        product.setId(1);
        product.setName("iPhone 15 Pro Max");
        product.setDescription("Best Phone");
        product.setPrice(799.99);
        product.setQuantity(1000);
        ResponseEntity<Void> responseEntity = createProductCommandHandler.execute(product);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void createProductCommandHandler_invalidPrice_throwsInvalidPriceException(){
        Product product = new Product();
        product.setId(1);
        product.setName("iPhone 15 Pro Max");
        product.setDescription("Best Phone");
        product.setPrice(-799.99);
        product.setQuantity(1000);
        ProductNotValidException exception = assertThrows(ProductNotValidException.class, () ->
                createProductCommandHandler.execute(product));
        assertEquals("Product price cannot be negative", exception.getSimpleResponse().getMessage());
    }
}
