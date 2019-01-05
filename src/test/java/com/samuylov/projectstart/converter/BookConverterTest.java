package com.samuylov.projectstart.converter;

import com.samuylov.projectstart.MockData;
import com.samuylov.projectstart.dto.BookDto;
import com.samuylov.projectstart.entity.BookEntity;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BookConverterTest {

    private final BookConverter personConverter = new BookConverter();

    @Test
    public void convertToDto() {
        final BookEntity bookEntity = MockData.bookDbo();
        final BookDto bookDto = personConverter.convertToDto(bookEntity);
        assertEquals(bookEntity.getName(), bookDto.getName());
        assertEquals(bookEntity.getDescription(), bookDto.getDescription());
    }

    @Test
    public void convertToDbo() {
       /* final BookDto bookDto = MockData.bookDto();
        final BookEntity bookDbo = personConverter.convertToDbo(bookDto);
        assertEquals(bookDto.getName(), bookDbo.getName());
        assertEquals(bookDto.getDescription(), bookDbo.getDescription());*/
    }
}
