package com.example.sbpostgresqltutorial;

public interface Command <E, T>{
    T execute(E entity);
}
