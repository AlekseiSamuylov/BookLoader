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
    private int rating;
    private List<ReviewDto> reviews;
    private List<CommentDto> comments;

    public BookDto(final Long id, final String name, final String description, final int rating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rating = rating;
    }

    public void incrementRating() {
        rating++;
    }
    public void decrementRating() {
        rating--;
    }
}
