package com.samuylov.projectstart.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
@Table(name = "BOOK")
public class BookEntity extends AbstractEntity {

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private Long rating;
}