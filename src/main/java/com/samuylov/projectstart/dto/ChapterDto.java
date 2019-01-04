package com.samuylov.projectstart.dto;

import com.samuylov.projectstart.entity.BookDbo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChapterDto {
    private Long id;
    private Long number;
    private String name;
    private String text;
    private BookDbo book;

    public ChapterDto(final Long number, final String name, final String text) {
        this.number = number;
        this.name = name;
        this.text = text;
    }
}