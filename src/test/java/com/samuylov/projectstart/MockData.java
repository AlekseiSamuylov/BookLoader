package com.samuylov.projectstart;

import com.samuylov.projectstart.dto.BookDto;
import com.samuylov.projectstart.entity.BookEntity;

public class MockData {

    public static BookEntity bookDbo() {
        final BookEntity personDbo = new BookEntity();
        personDbo.setName("bookDbo name");
        personDbo.setDescription("bookDbo description");
        return personDbo;
    }

    public static BookDto bookDto() {
        final BookDto personDto = new BookDto();
        personDto.setName("bookDto name");
        personDto.setDescription("bookDto description");
        return personDto;
    }
}
