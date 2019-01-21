package com.samuylov.projectstart.service;

import com.samuylov.projectstart.converter.DtoEntityConverter;
import com.samuylov.projectstart.dto.ReviewDto;
import com.samuylov.projectstart.entity.ReviewEntity;
import com.samuylov.projectstart.repository.NamedEntityRepository;
import com.samuylov.projectstart.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService extends AbstractService<ReviewDto, ReviewEntity> implements FindElementsService<ReviewDto> {

    public ReviewService(final NamedEntityRepository<ReviewEntity> repository,
                         final DtoEntityConverter<ReviewDto, ReviewEntity> converter) {
        super(repository, converter);
    }

    @Override
    public void create(final ReviewDto reviewDto) {
        reviewDto.setDate(new Date());
        repository.save(converter.convertToEntity(reviewDto));
    }

    @Override
    public void update(final Long reviewId, final ReviewDto reviewDto) {
        final ReviewEntity oldReview = ((ReviewRepository) repository).findFirstById(reviewId);

        oldReview.setText(reviewDto.getText());
        repository.save(oldReview);
    }

    @Override
    public void save(final ReviewDto reviewDto) {
        if (reviewDto.getId() == null) {
            create(reviewDto);
        } else {
            update(reviewDto.getId(), reviewDto);
        }
    }

    @Override
    public void delete(final Long reviewId) {
        repository.deleteById(reviewId);
    }

    @Override
    public List<ReviewDto> getList() {
        return repository.findAll().stream().map(converter::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<ReviewDto> getAllByBookId(final Long bookId) {
        return ((ReviewRepository) repository).findAllByBookIdOrderByDateDesc(bookId).
                stream().map(converter::convertToDto).collect(Collectors.toList());
    }

    public void deleteAllReviewsByBookId(final Long bookId) {
        ((ReviewRepository) repository).deleteAllByBookId(bookId);
    }
}
