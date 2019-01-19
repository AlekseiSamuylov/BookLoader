package com.samuylov.projectstart.service;

import com.samuylov.projectstart.dto.AbstractDto;

import java.util.List;

public interface FindElementsService<DtoClass extends AbstractDto> {
    List<DtoClass> getAllByBookId(final Long bookId);
}
