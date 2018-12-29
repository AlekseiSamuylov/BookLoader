package com.samuylov.projectstart.converter;

import com.samuylov.projectstart.entity.BookDbo;
import com.samuylov.projectstart.dto.BookDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BookConverter implements DtoDboConverter<BookDto, BookDbo> {
    @Override
    public BookDto convertToDto(final BookDbo dbo) {
        final BookDto bookDto = new BookDto();
        BeanUtils.copyProperties(dbo, bookDto);
        return bookDto;
    }

    @Override
    public BookDbo convertToDbo(final BookDto dto) {
        final BookDbo bookDbo = new BookDbo();
        BeanUtils.copyProperties(dto, bookDbo);
        return bookDbo;
    }
}
