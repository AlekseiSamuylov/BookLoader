package com.samuylov.projectstart;

import com.samuylov.projectstart.dto.BookDto;
import com.samuylov.projectstart.dto.ChapterDto;
import com.samuylov.projectstart.entity.BookEntity;
import com.samuylov.projectstart.entity.ChapterEntity;

public class MockDataChapter {

    public static ChapterEntity chapterDbo() {
        final ChapterEntity chapterEntity = new ChapterEntity();
        chapterEntity.setId(1L);
        chapterEntity.setName("chapterEntity name");
        chapterEntity.setText("chapterEntity text");
        chapterEntity.setNumber(12L);
        chapterEntity.setBook(new BookEntity(1L,"book", "desc", 0));
        return chapterEntity;
    }

    public static ChapterDto chapterDto() {
        final ChapterDto chapterDto = new ChapterDto();
        chapterDto.setId(1L);
        chapterDto.setName("chapterDto name");
        chapterDto.setText("chapterDto text");
        chapterDto.setNumber(12L);
        chapterDto.setBook(new BookDto(1L, "book", "desc", 0));
        return chapterDto;
    }
}