package com.samuylov.projectstart.service;

import com.samuylov.projectstart.converter.ReviewConverter;
import com.samuylov.projectstart.dto.ReviewDto;
import com.samuylov.projectstart.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewConverter reviewConverter;

    @Autowired
    public ReviewService(final ReviewRepository reviewRepository, final ReviewConverter reviewConverter) {
        this.reviewRepository = reviewRepository;
        this.reviewConverter = reviewConverter;
    }

    public void createReview(final ReviewDto reviewDto) {
        reviewRepository.save(reviewConverter.convertToEntity(reviewDto));
    }

    public List<ReviewDto> getAllByBookId(final long bookId) {
        return reviewRepository.findAllByBookIdOrderByDateDesc(bookId).
                stream().map(reviewConverter::convertToDto).collect(Collectors.toList());
    }
}
