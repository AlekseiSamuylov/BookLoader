package com.samuylov.projectstart.service;

import com.samuylov.projectstart.converter.ReviewConverter;
import com.samuylov.projectstart.dto.ReviewDto;
import com.samuylov.projectstart.entity.ReviewEntity;
import com.samuylov.projectstart.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewConverter reviewConverter;

    public void createReview(final ReviewDto reviewDto) {
        reviewDto.setDate(new Date());
        reviewRepository.save(reviewConverter.convertToEntity(reviewDto));
    }

    public void updateReview(final Long reviewId, final ReviewDto reviewDto) {
        final ReviewEntity oldReview = reviewRepository.getOne(reviewId);

        oldReview.setText(reviewDto.getText());
        reviewRepository.save(oldReview);
    }

    public void deleteReview(final Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    public List<ReviewDto> getAllByBookId(final Long bookId) {
        return reviewRepository.findAllByBookIdOrderByDateDesc(bookId).
                stream().map(reviewConverter::convertToDto).collect(Collectors.toList());
    }

    public void deleteAllReviewsByBookId(final Long bookId) {
        reviewRepository.deleteAllByBookId(bookId);
    }
}
