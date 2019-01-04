package com.samuylov.projectstart.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BookDto {
    private Long id;
    private String name;
    private String description;
    private List<ChapterDto> chapterList;
}
