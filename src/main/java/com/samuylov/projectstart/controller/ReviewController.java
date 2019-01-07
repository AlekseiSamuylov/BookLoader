package com.samuylov.projectstart.controller;

import com.samuylov.projectstart.dto.ReviewDto;
import com.samuylov.projectstart.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/book/review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/createReview")
    public ResponseEntity createReview(@RequestBody final ReviewDto reviewDto) {
        reviewService.createReview(reviewDto);
        return new ResponseEntity<>("Review added.", HttpStatus.CREATED);
    }
}
