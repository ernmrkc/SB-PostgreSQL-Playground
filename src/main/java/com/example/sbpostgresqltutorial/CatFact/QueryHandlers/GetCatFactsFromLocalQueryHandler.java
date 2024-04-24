package com.example.sbpostgresqltutorial.CatFact.QueryHandlers;

import com.example.sbpostgresqltutorial.CatFact.CatFactRepository;
import com.example.sbpostgresqltutorial.CatFact.Model.CatFact;
import com.example.sbpostgresqltutorial.CatFact.Model.CatFactEntity;
import com.example.sbpostgresqltutorial.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetCatFactsFromLocalQueryHandler implements Query<Void, List<CatFact>> {

    private final CatFactRepository catFactRepository;
    public GetCatFactsFromLocalQueryHandler(CatFactRepository catFactRepository) {
        this.catFactRepository = catFactRepository;
    }

    @Override
    public ResponseEntity<List<CatFact>> execute(Void input) {
        return ResponseEntity.ok(catFactRepository
                .findAll()
                .stream()
                .map(CatFactEntity::convertToCatFact)
                .collect(Collectors.toList()));
    }
}
