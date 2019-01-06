package com.samuylov.projectstart.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
@Table(name = "CHAPTER")
public class ChapterDbo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long number;

    @NotNull
    private String name;

    @NotNull
    private String text;

    @ManyToOne
    @JoinColumn(name = "book")
    private BookDbo book;

    public ChapterDbo(@NotNull final Long number, @NotNull final String name, @NotNull final String text) {
        this.number = number;
        this.name = name;
        this.text = text;
    }
}