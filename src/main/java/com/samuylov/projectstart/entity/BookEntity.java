package com.samuylov.projectstart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "BOOK")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String description;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "book")
    @JsonIgnore
    private List<ChapterEntity> chapterList;

    @NotNull
    private int rating;

    public BookEntity(final Long id, @NonNull final String name, @NonNull final String description, @NotNull final int rating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rating = rating;
    }
}