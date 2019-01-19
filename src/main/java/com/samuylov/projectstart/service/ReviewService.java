package com.samuylov.projectstart.service;

import com.samuylov.projectstart.converter.ReviewConverter;
import com.samuylov.projectstart.dto.ReviewDto;
import com.samuylov.projectstart.entity.ReviewEntity;
import com.samuylov.projectstart.repository.ReviewRepository;
import com.vaadin.data.provider.Query;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class ReviewService extends AbstractService<ReviewDto> implements FindElementsService<ReviewDto> {

    private final ReviewRepository reviewRepository;
    private final ReviewConverter reviewConverter;

    @Override
    public void create(final ReviewDto reviewDto) {
        reviewDto.setDate(new Date());
        reviewRepository.save(reviewConverter.convertToEntity(reviewDto));
    }

    @Override
    public void update(final Long reviewId, final ReviewDto reviewDto) {
        final ReviewEntity oldReview = reviewRepository.findFirstById(reviewId);

        oldReview.setText(reviewDto.getText());
        reviewRepository.save(oldReview);
    }

    @Override
    public void delete(final Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    @Override
    public List<ReviewDto> getList() {
        return reviewRepository.findAll().stream().map(reviewConverter::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<ReviewDto> getAllByBookId(final Long bookId) {
        return reviewRepository.findAllByBookIdOrderByDateDesc(bookId).
                stream().map(reviewConverter::convertToDto).collect(Collectors.toList());
    }

    public void deleteAllReviewsByBookId(final Long bookId) {
        reviewRepository.deleteAllByBookId(bookId);
    }

    @Override
    public Stream<ReviewDto> findWithPagination(final Query<ReviewDto, String> query) {
        final PageRequest pageRequest = preparePageRequest(query);
        final List<ReviewDto> items = reviewRepository.findAll(pageRequest)
                .stream().map(reviewConverter::convertToDto).collect(Collectors.toList());
        return items.stream();
    }
}
