package com.samuylov.projectstart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "BOOK")
public class BookDbo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "book")
    @JsonIgnore
    private List<ChapterDbo> chapterList;

    public BookDbo(final Long id, @NonNull final String name, @NonNull final String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
