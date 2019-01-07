package com.samuylov.projectstart.controller;

import com.samuylov.projectstart.dto.ReviewDto;
import com.samuylov.projectstart.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/update/{reviewId}")
    public ResponseEntity updateReview(@PathVariable final Long reviewId, @RequestBody final ReviewDto reviewDto) {
        reviewService.updateReview(reviewId, reviewDto);
        return new ResponseEntity<>("Review updated.", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity deleteReview(@PathVariable final Long reviewId) {
        reviewService.deleteReview(reviewId);
        return new ResponseEntity<>("Review deleted.", HttpStatus.OK);
    }

    @GetMapping("/list/{reviewId}")
    public ResponseEntity getAllReviewsByBookId(@PathVariable final Long reviewId) {
        return new ResponseEntity<>(reviewService.getAllByBookId(reviewId), HttpStatus.FOUND);
    }
}
