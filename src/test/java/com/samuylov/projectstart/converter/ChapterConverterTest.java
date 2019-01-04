package com.samuylov.projectstart.converter;

import com.samuylov.projectstart.MockDataChapter;
import com.samuylov.projectstart.dto.ChapterDto;
import com.samuylov.projectstart.entity.ChapterDbo;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChapterConverterTest {

    private final ChapterConverter chapterConverter = new ChapterConverter();

    @Test
    public void convertToDto() {
        final ChapterDbo chapterDbo = MockDataChapter.chapterDbo();
        final ChapterDto chapterDto = chapterConverter.convertToDto(chapterDbo);
        assertEquals(chapterDbo.getName(), chapterDto.getName());
        assertEquals(chapterDbo.getText(), chapterDto.getText());
        assertEquals(chapterDbo.getNumber(), chapterDto.getNumber());
        assertEquals(chapterDbo.getBook(), chapterDto.getBook());
    }

    @Test
    public void convertToDbo() {
        final ChapterDto chapterDto = MockDataChapter.chapterDto();
        final ChapterDbo chapterDbo = chapterConverter.convertToDbo(chapterDto);
        assertEquals(chapterDto.getName(), chapterDbo.getName());
        assertEquals(chapterDto.getText(), chapterDbo.getText());
        assertEquals(chapterDto.getNumber(), chapterDbo.getNumber());
        assertEquals(chapterDto.getBook(), chapterDbo.getBook());
    }
}