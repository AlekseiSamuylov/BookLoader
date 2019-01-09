package com.samuylov.projectstart.controller;

import com.samuylov.projectstart.dto.ReviewDto;
import com.samuylov.projectstart.service.ReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Api(tags = "All requests for work with reviews", description = "And them")
@RequestMapping("/book/review")
public class ReviewController {

    private final ReviewService reviewService;

    @ApiOperation(tags = "Create requests", value = "Create review by name text",
            notes = "Beautiful create review")
    @PostMapping("/createReview")
    public ResponseEntity createReview(@RequestBody final ReviewDto reviewDto) {
        reviewService.createReview(reviewDto);
        return new ResponseEntity<>("Review added.", HttpStatus.CREATED);
    }

    @ApiOperation(tags = "Update requests", value = "Update review by review id and old review params",
            notes = "Beautiful update review")
    @PutMapping("/update/{reviewId}")
    public ResponseEntity updateReview(@PathVariable final Long reviewId, @RequestBody final ReviewDto reviewDto) {
        reviewService.updateReview(reviewId, reviewDto);
        return new ResponseEntity<>("Review updated.", HttpStatus.OK);
    }

    @ApiOperation(tags = "Delete requests", value = "Delete review by review id",
            notes = "Beautiful delete review")
    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity deleteReview(@PathVariable final Long reviewId) {
        reviewService.deleteReview(reviewId);
        return new ResponseEntity<>("Review deleted.", HttpStatus.OK);
    }

    @ApiOperation(tags = "Get element(s) requests", value = "Get all reviews by book id",
            notes = "Beautiful get reviews")
    @GetMapping("/list/{bookId}")
    public ResponseEntity getAllReviewsByBookId(@PathVariable final Long bookId) {
        return new ResponseEntity<>(reviewService.getAllByBookId(bookId), HttpStatus.FOUND);
    }
}
