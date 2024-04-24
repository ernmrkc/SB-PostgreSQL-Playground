package com.example.sbpostgresqltutorial.Product.Model;

import lombok.Data;

@Data
public class ProductSearchRequest {
    private String name;
    private String description;

    public ProductSearchRequest(String name, String description){
        this.name = name;
        this.description = description;
    }
}
