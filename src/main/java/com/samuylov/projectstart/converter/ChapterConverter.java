package com.samuylov.projectstart.converter;

import com.samuylov.projectstart.dto.BookDto;
import com.samuylov.projectstart.dto.ChapterDto;
import com.samuylov.projectstart.entity.ChapterEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChapterConverter implements DtoEntityConverter<ChapterDto, ChapterEntity> {

    private final BookConverter bookConverter;

    @Autowired
    public ChapterConverter(final BookConverter bookConverter) {
        this.bookConverter = bookConverter;
    }

    @Override
    public ChapterDto convertToDto(final ChapterEntity dbo) {
        final ChapterDto chapterDto = new ChapterDto();
        chapterDto.setNumber(dbo.getNumber());
        chapterDto.setName(dbo.getName());
        chapterDto.setText(dbo.getText());
        chapterDto.setId(dbo.getId());
        final BookDto bookDto = bookConverter.convertToDto(dbo.getBook());
        chapterDto.setBook(bookDto);
        return chapterDto;
    }

    @Override
    public ChapterEntity convertToEntity(final ChapterDto dto) {
        final ChapterEntity chapterEntity = new ChapterEntity();
        BeanUtils.copyProperties(dto, chapterEntity);
        return chapterEntity;
    }
}