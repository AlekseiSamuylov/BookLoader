package com.samuylov.projectstart;

import com.samuylov.projectstart.dto.BookDto;
import com.samuylov.projectstart.entity.BookDbo;

public class MockData {

    public static BookDbo bookDbo() {
        final BookDbo personDbo = new BookDbo();
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
