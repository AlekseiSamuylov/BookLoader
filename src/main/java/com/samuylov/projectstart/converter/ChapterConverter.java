package com.samuylov.projectstart.converter;

import com.samuylov.projectstart.dto.ChapterDto;
import com.samuylov.projectstart.entity.ChapterDbo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ChapterConverter implements DtoDboConverter<ChapterDto, ChapterDbo> {
    @Override
    public ChapterDto convertToDto(final ChapterDbo dbo) {
        final ChapterDto chapterDto = new ChapterDto();
        BeanUtils.copyProperties(dbo, chapterDto);
        return chapterDto;
    }

    @Override
    public ChapterDbo convertToDbo(final ChapterDto dto) {
        final ChapterDbo chapterDbo = new ChapterDbo();
        BeanUtils.copyProperties(dto, chapterDbo);
        return chapterDbo;
    }
}