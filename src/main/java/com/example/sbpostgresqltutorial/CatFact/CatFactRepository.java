package com.example.sbpostgresqltutorial.CatFact;

import com.example.sbpostgresqltutorial.CatFact.Model.CatFactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatFactRepository extends JpaRepository<CatFactEntity, Integer> {

}
