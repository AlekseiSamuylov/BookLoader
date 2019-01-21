package com.samuylov.projectstart.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
@Table(name = "CHAPTER")
public class ChapterEntity extends AbstractEntity {

    @NotNull
    private Long number;

    @NotNull
    private String name;

    @NotNull
    private String text;

    @NotNull
    private Long bookId;
}