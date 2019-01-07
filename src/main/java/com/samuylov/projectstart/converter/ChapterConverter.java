package com.samuylov.projectstart.converter;

import com.samuylov.projectstart.dto.ChapterDto;
import com.samuylov.projectstart.entity.ChapterEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ChapterConverter implements DtoEntityConverter<ChapterDto, ChapterEntity> {

    @Override
    public ChapterDto convertToDto(final ChapterEntity dbo) {
        final ChapterDto chapterDto = new ChapterDto();
        BeanUtils.copyProperties(dbo, chapterDto);
        return chapterDto;
    }

    @Override
    public ChapterEntity convertToEntity(final ChapterDto dto) {
        final ChapterEntity chapterEntity = new ChapterEntity();
        BeanUtils.copyProperties(dto, chapterEntity);
        return chapterEntity;
    }
}