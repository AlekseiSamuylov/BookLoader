package com.samuylov.projectstart.service;

import com.samuylov.projectstart.MockDataChapter;
import com.samuylov.projectstart.converter.ChapterConverter;
import com.samuylov.projectstart.dto.ChapterDto;
import com.samuylov.projectstart.entity.BookEntity;
import com.samuylov.projectstart.entity.ChapterDbo;
import com.samuylov.projectstart.repository.ChapterRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ChapterServiceTest {

    @InjectMocks
    private ChapterService chapterService;

    @Mock
    private ChapterRepository chapterRepository;

    @Spy
    private ChapterConverter chapterConverter;

    @Test
    public void createChapter() {
        final ChapterDbo chapterDbo = new ChapterDbo();
        chapterDbo.setNumber(1L);
        chapterDbo.setName("name");
        chapterDbo.setText("text");
        chapterDbo.setBook(new BookEntity(1L, "book", "desc"));
        doReturn(chapterDbo).when(chapterRepository).save(any(ChapterDbo.class));

        chapterService.createChapter(new ChapterDto());

        verify(chapterRepository, times(1)).save(any(ChapterDbo.class));
    }

    @Test
    public void getChapterList() {
        final List<ChapterDbo> findAllResult = new ArrayList<>();
        final ChapterDbo chapterDbo = MockDataChapter.chapterDbo();
        findAllResult.add(chapterDbo);
        findAllResult.add(chapterDbo);
        findAllResult.add(chapterDbo);
        findAllResult.add(chapterDbo);
        doReturn(findAllResult).when(chapterRepository).findAll();

        final List<ChapterDto> chapterList = chapterService.getChapterList();

        verify(chapterRepository, times(1)).findAll();
        assertEquals(findAllResult.size(), chapterList.size());
        for (final ChapterDto chapterDto : chapterList) {
            assertEquals(chapterDbo.getName(), chapterDto.getName());
            assertEquals(chapterDbo.getText(), chapterDto.getText());
            assertEquals(chapterDbo.getNumber(), chapterDto.getNumber());
            assertEquals(chapterDbo.getBook(), chapterDto.getBook());
        }
    }

    @Test
    public void deleteChapter() {
        doNothing().when(chapterRepository).deleteById(1L);
        chapterService.deleteChapter(1L, 1L);
        verify(chapterRepository, times(1)).deleteByBookIdAndNumber(1L, 1L);
    }

    @Test
    public void getChapterByBookIdAndNumber() {
        final ChapterDbo chapterDbo = MockDataChapter.chapterDbo();
        doReturn(chapterDbo).when(chapterRepository).findByBookIdAndNumber(1L, 2L);
        final ChapterDto chapterDto = chapterService.getChapterByBookIdAndNumber(1L, 2L);
        verify(chapterRepository, times(1)).findByBookIdAndNumber(1L, 2L);
    }

    @Test
    public void updateChapter() {
        final ChapterDbo chapterDbo = MockDataChapter.chapterDbo();
        doReturn(chapterDbo).when(chapterRepository).findByBookIdAndNumber(1L, 2L);

        final ChapterDto chapterDbo1 = new ChapterDto();
        chapterDbo1.setNumber(1L);
        chapterDbo1.setName("booook1");
        chapterDbo1.setText("text");
        chapterDbo1.setBook(new BookEntity(1L, "book", "desc"));
        doReturn(chapterDbo).when(chapterRepository).save(any(ChapterDbo.class));

        chapterService.updateChapter(1L, 2L, chapterDbo1);
        verify(chapterRepository, times(1)).save(any(ChapterDbo.class));
    }
}