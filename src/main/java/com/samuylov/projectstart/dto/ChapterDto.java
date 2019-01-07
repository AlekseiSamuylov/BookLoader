package com.samuylov.projectstart.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChapterDto {
    private Long id;
    private Long number;
    private String name;
    private String text;
    private Long bookId;
}