package com.samuylov.projectstart.converter;

import com.samuylov.projectstart.dto.CommentDto;
import com.samuylov.projectstart.entity.CommentEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CommentConverter implements DtoEntityConverter<CommentDto, CommentEntity> {
    @Override
    public CommentDto convertToDto(final CommentEntity entity) {
        final CommentDto commentDto = new CommentDto();
        BeanUtils.copyProperties(entity, commentDto);
        return commentDto;
    }

    @Override
    public CommentEntity convertToEntity(final CommentDto dto) {
        final CommentEntity commentEntity = new CommentEntity();
        BeanUtils.copyProperties(dto, commentEntity);
        return commentEntity;
    }
}
