package com.example.sbpostgresqltutorial.Validation;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/*
 * @NotNull
 * @NotEmpty
 * @Size
 * @Email
 * @Pattern
 * @AssertTrue
 * @FutureOrPresent
 * @CreditCard
 *
 * or go to Jakarta Bean Validation specification
 */

@Entity
@Data
public class Post {
    @Id
    private String id;

    @NotNull(message = "name cannot be null")
    private String name;

    @Size(min = 5, message = "Description too short, must be 5 characters long")
    private String description;
}
