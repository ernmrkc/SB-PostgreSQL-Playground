package com.example.sbpostgresqltutorial.CatFact.QueryHandlers;

import com.example.sbpostgresqltutorial.CatFact.Model.CatFact;
import com.example.sbpostgresqltutorial.CatFact.Model.CatFactDTO;
import com.example.sbpostgresqltutorial.Exceptions.ExternalCatFactsDownException;
import com.example.sbpostgresqltutorial.Exceptions.SimpleResponse;
import com.example.sbpostgresqltutorial.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GetCatFactFromExternalAPIQueryHandler implements Query<Void, CatFactDTO> {

    private final static String CAT_FACT_URL = "https://catfact.ninja/fact";
    private final RestTemplate restTemplate;

    public GetCatFactFromExternalAPIQueryHandler(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<CatFactDTO> execute(Void input) {
        CatFact catFact = getCatFact();
        CatFactDTO catFactDTO = new CatFactDTO(catFact.getFact());
        return ResponseEntity.ok(catFactDTO);
    }

    public CatFact getCatFact(){
        try{
            return restTemplate.getForObject(CAT_FACT_URL, CatFact.class);
        }catch (Exception exception){
            throw new ExternalCatFactsDownException(HttpStatus.SERVICE_UNAVAILABLE, new SimpleResponse("The External API is down."));
        }
    }

}
