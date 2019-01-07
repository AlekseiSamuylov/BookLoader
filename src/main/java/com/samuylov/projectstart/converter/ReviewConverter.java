package com.samuylov.projectstart.converter;

import com.samuylov.projectstart.dto.ReviewDto;
import com.samuylov.projectstart.entity.ReviewEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ReviewConverter implements DtoEntityConverter<ReviewDto, ReviewEntity> {

    @Override
    public ReviewDto convertToDto(final ReviewEntity entity) {
        final ReviewDto reviewDto = new ReviewDto();
        BeanUtils.copyProperties(entity, reviewDto);
        return reviewDto;
    }

    @Override
    public ReviewEntity convertToEntity(final ReviewDto dto) {
        final ReviewEntity reviewEntity = new ReviewEntity();
        BeanUtils.copyProperties(dto, reviewEntity);
        return reviewEntity;
    }
}
