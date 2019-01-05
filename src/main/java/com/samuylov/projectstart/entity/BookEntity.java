package com.samuylov.projectstart.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
@Table(name = "BOOK")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String name;

    @NonNull
    private String description;

    @NotNull
    private int rating;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "book")
    @JsonIgnore
    private List<ChapterDbo> chapterList;

    public BookEntity(final Long id, @NonNull final String name, @NonNull final String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
