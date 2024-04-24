package com.example.sbpostgresqltutorial.CatFact.Model;

import com.example.sbpostgresqltutorial.Helpers.JsonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "cat_facts")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CatFactEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cat_FactJSON", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private String catFactJSON;

    public CatFactEntity(CatFact catFact){
        // this.catFactJSON = convertToJSON(catFact);
        this.catFactJSON = JsonUtil.toJson(catFact);
    }

    // Serialization
    private String convertToJSON(CatFact catFact){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(catFact);
        }catch (Exception e){
            throw new RuntimeException("JSON Parse error.");
        }
    }

    // Deserialization
    public CatFact convertToCatFact(){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(catFactJSON, CatFact.class);
        }catch (Exception e){
            throw new RuntimeException("JSON Parse Error");
        }
    }
}
