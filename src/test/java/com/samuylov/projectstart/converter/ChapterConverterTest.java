package com.samuylov.projectstart.converter;

import com.samuylov.projectstart.MockDataChapter;
import com.samuylov.projectstart.dto.BookDto;
import com.samuylov.projectstart.dto.ChapterDto;
import com.samuylov.projectstart.entity.BookEntity;
import com.samuylov.projectstart.entity.ChapterEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ChapterConverterTest {

    @InjectMocks
    private ChapterConverter chapterConverter;

    @Mock
    private BookConverter bookConverter;

    @Test
    public void convertToDto() {
        final ChapterEntity chapterEntity = MockDataChapter.chapterDbo();
        final ChapterDto chapterDto = chapterConverter.convertToDto(chapterEntity);
        assertEquals(chapterEntity.getName(), chapterDto.getName());
        assertEquals(chapterEntity.getText(), chapterDto.getText());
        assertEquals(chapterEntity.getNumber(), chapterDto.getNumber());
        assertEquals(chapterEntity.getBookId(), chapterDto.getBookId());
    }

    @Test
    public void convertToDbo() {
        final ChapterDto chapterDto = MockDataChapter.chapterDto();
        final ChapterEntity chapterEntity = chapterConverter.convertToEntity(chapterDto);
        assertEquals(chapterDto.getName(), chapterEntity.getName());
        assertEquals(chapterDto.getText(), chapterEntity.getText());
        assertEquals(chapterDto.getNumber(), chapterEntity.getNumber());
        assertEquals(chapterDto.getBookId(), chapterEntity.getBookId());
    }
}