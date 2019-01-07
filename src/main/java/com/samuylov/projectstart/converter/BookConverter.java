package com.samuylov.projectstart.converter;

import com.samuylov.projectstart.entity.BookEntity;
import com.samuylov.projectstart.dto.BookDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BookConverter implements DtoEntityConverter<BookDto, BookEntity> {
    @Override
    public BookDto convertToDto(final BookEntity entity) {
        final BookDto bookDto = new BookDto();
        BeanUtils.copyProperties(entity, bookDto, "review", "comment", "chapter");
        return bookDto;
    }

    @Override
    public BookEntity convertToEntity(final BookDto dto) {
        final BookEntity bookEntity = new BookEntity();
        BeanUtils.copyProperties(dto, bookEntity, "review", "comment", "chapter");
        return bookEntity;
    }
}
