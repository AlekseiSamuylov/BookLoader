package com.samuylov.projectstart.converter;

import com.samuylov.projectstart.entity.BookEntity;
import com.samuylov.projectstart.dto.BookDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BookConverter implements DtoDboConverter<BookDto, BookEntity> {
    @Override
    public BookDto convertToDto(final BookEntity dbo) {
        final BookDto bookDto = new BookDto();
        BeanUtils.copyProperties(dbo, bookDto);
        return bookDto;
    }

    @Override
    public BookEntity convertToDbo(final BookDto dto) {
        final BookEntity bookEntity = new BookEntity();
        BeanUtils.copyProperties(dto, bookEntity);
        return bookEntity;
    }
}
