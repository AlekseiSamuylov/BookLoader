package com.samuylov.projectstart.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookDto {
    private long id;
    private String name;
    private String description;
}
