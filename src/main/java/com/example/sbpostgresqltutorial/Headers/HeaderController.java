package com.example.sbpostgresqltutorial.Headers;

import com.example.sbpostgresqltutorial.Product.Model.Product;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeaderController {

    @GetMapping("/header")
    public String getRegionalRequest(@RequestHeader(required = false, defaultValue = "USA") String region){
        if(region.equals("USA")) return "Bald Eagle Freedom";
        if(region.equals("CAN")) return "I bleed maple syrup";
        return "Country not supported";
    }

    @GetMapping(value = "/header-format", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Product> getProduct(){
        Product product = new Product();
        product.setName("myProduct");
        product.setId(1);
        product.setDescription("greatest product ever");
        return ResponseEntity.ok(product);
    }
}
