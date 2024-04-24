package com.example.sbpostgresqltutorial.CatFact;

import com.example.sbpostgresqltutorial.CatFact.CommandHandlers.SaveCatFactCommandHandler;
import com.example.sbpostgresqltutorial.CatFact.Model.CatFact;
import com.example.sbpostgresqltutorial.CatFact.Model.CatFactDTO;
import com.example.sbpostgresqltutorial.CatFact.QueryHandlers.GetCatFactFromExternalAPIQueryHandler;
import com.example.sbpostgresqltutorial.CatFact.QueryHandlers.GetCatFactsFromLocalQueryHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catfact")
public class CatFactController {

    private final GetCatFactFromExternalAPIQueryHandler getCatFactFromExternalAPIQueryHandler;
    private final GetCatFactsFromLocalQueryHandler getCatFactsFromLocalQueryHandler;
    private final SaveCatFactCommandHandler saveCatFactCommandHandler;

    public CatFactController(GetCatFactFromExternalAPIQueryHandler getCatFactFromExternalAPIQueryHandler,
                             GetCatFactsFromLocalQueryHandler getCatFactsFromLocalQueryHandler,
                             SaveCatFactCommandHandler saveCatFactCommandHandler){
        this.getCatFactFromExternalAPIQueryHandler = getCatFactFromExternalAPIQueryHandler;
        this.getCatFactsFromLocalQueryHandler = getCatFactsFromLocalQueryHandler;
        this.saveCatFactCommandHandler = saveCatFactCommandHandler;
    }

    @GetMapping
    public ResponseEntity<CatFactDTO> getCatFact(){
        return getCatFactFromExternalAPIQueryHandler.execute(null);
    }

    @GetMapping("/local-save")
    public ResponseEntity<CatFactDTO> saveCatFact(){
         CatFact catFact = getCatFactFromExternalAPIQueryHandler.getCatFact();
        return saveCatFactCommandHandler.execute(catFact);
    }

    @GetMapping("/local-get")
    public ResponseEntity<List<CatFact>> getSavedCatFacts(){
        return getCatFactsFromLocalQueryHandler.execute(null);
    }
}
