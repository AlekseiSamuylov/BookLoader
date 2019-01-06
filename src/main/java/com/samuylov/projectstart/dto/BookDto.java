package com.samuylov.projectstart.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BookDto {
    private long id;
    private String name;
    private String description;
    private int rating;
    private List<ReviewDto> reviews;
    private List<CommentDto> comments;
    public void incrementRating() {
        rating++;
    }
    public void decrementRating() {
        rating--;
    }
}