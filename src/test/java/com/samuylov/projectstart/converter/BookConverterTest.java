package com.samuylov.projectstart.converter;

import com.samuylov.projectstart.MockData;
import com.samuylov.projectstart.dto.BookDto;
import com.samuylov.projectstart.entity.BookEntity;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BookConverterTest {

    private final BookConverter bookConverter = new BookConverter();

    @Test
    public void convertToDto() {
        final BookEntity bookDbo = MockData.bookDbo();
        final BookDto bookDto = bookConverter.convertToDto(bookDbo);
        assertEquals(bookDbo.getName(), bookDto.getName());
        assertEquals(bookDbo.getDescription(), bookDto.getDescription());
    }

    @Test
    public void convertToDbo() {
        final BookDto bookDto = MockData.bookDto();
        final BookEntity bookDbo = bookConverter.convertToEntity(bookDto);
        assertEquals(bookDto.getName(), bookDbo.getName());
        assertEquals(bookDto.getDescription(), bookDbo.getDescription());
    }
}
