package com.samuylov.projectstart.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
@Table(name = "BOOK")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private int rating;

    public BookEntity(final Long id, @NotNull final String name, @NotNull final String description, @NotNull final int rating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rating = rating;
    }
}