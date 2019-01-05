package com.samuylov.projectstart;

import com.samuylov.projectstart.dto.ChapterDto;
import com.samuylov.projectstart.entity.BookEntity;
import com.samuylov.projectstart.entity.ChapterDbo;

public class MockDataChapter {

    public static ChapterDbo chapterDbo() {
        final ChapterDbo chapterDbo = new ChapterDbo();
        chapterDbo.setName("chapterDbo name");
        chapterDbo.setText("chapterDbo text");
        chapterDbo.setNumber(12L);
        chapterDbo.setBook(new BookEntity(1L,"book", "desc"));
        return chapterDbo;
    }

    public static ChapterDto chapterDto() {
        final ChapterDto chapterDto = new ChapterDto();
        chapterDto.setName("chapterDto name");
        chapterDto.setText("chapterDto text");
        chapterDto.setNumber(12L);
        chapterDto.setBook(new BookEntity(1L, "book", "desc"));
        return chapterDto;
    }
}