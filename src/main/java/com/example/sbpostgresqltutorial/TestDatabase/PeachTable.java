package com.example.sbpostgresqltutorial.TestDatabase;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "peach_table")
public class PeachTable {

    @Id
    private int Id;

    @Column(name = "type_of_fruit")
    private String typeOfFruit;

    @Column(name = "price")
    private double price;

    private String color;

    private String myFavoriteColorType;
}
