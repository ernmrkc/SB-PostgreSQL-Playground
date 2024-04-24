package com.example.sbpostgresqltutorial.CatFact.CommandHandlers;

import com.example.sbpostgresqltutorial.CatFact.CatFactRepository;
import com.example.sbpostgresqltutorial.CatFact.Model.CatFact;
import com.example.sbpostgresqltutorial.CatFact.Model.CatFactDTO;
import com.example.sbpostgresqltutorial.CatFact.Model.CatFactEntity;
import com.example.sbpostgresqltutorial.Command;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SaveCatFactCommandHandler implements Command<CatFact, ResponseEntity<CatFactDTO>> {

    private final CatFactRepository catFactRepository;

    public SaveCatFactCommandHandler(CatFactRepository catFactRepository) {
        this.catFactRepository = catFactRepository;
    }

    @Override
    public ResponseEntity<CatFactDTO> execute(CatFact catFact) {
        CatFactEntity catFactEntity = new CatFactEntity(catFact);
        catFactRepository.save(catFactEntity);
        CatFactDTO catFactDTO = new CatFactDTO(catFact.getFact());
        return ResponseEntity.ok(catFactDTO);
    }
}
