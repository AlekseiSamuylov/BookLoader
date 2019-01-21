package com.samuylov.projectstart.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BookDto extends AbstractDto{
    private String name;
    private String description;
    private Long rating;
    private List<ChapterDto> chapters;
    private List<ReviewDto> reviews;
    private List<CommentDto> comments;
}
