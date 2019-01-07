package com.samuylov.projectstart.service;

import com.samuylov.projectstart.MockDataChapter;
import com.samuylov.projectstart.converter.BookConverter;
import com.samuylov.projectstart.converter.ChapterConverter;
import com.samuylov.projectstart.dto.ChapterDto;
import com.samuylov.projectstart.entity.ChapterEntity;
import com.samuylov.projectstart.repository.ChapterRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ChapterServiceTest {

    @InjectMocks
    private ChapterService chapterService;

    @Mock
    private ChapterRepository chapterRepository;

    @Mock
    private ChapterConverter chapterConverter;

    @Mock
    private BookConverter bookConverter;

    @Test
    public void createChapter() {
        final ChapterEntity chapterEntity = new ChapterEntity();
        chapterEntity.setNumber(1L);
        chapterEntity.setName("name");
        chapterEntity.setText("text");
        chapterEntity.setBookId(10L);
        doReturn(chapterEntity).when(chapterRepository).save(any(ChapterEntity.class));

        chapterService.createChapter(new ChapterDto());

        verify(chapterRepository, times(1)).save(any(ChapterEntity.class));
    }

//    @Test
//    public void getChapterList() {
//        final List<ChapterEntity> findAllResult = new ArrayList<>();
//        final ChapterEntity chapterEntity = MockDataChapter.chapterDbo();
//        findAllResult.add(chapterEntity);
//        doReturn(findAllResult).when(chapterRepository).findAll();
//
//        final List<ChapterDto> chapterList = chapterService.getChapterList();
//
//        verify(chapterRepository, times(1)).findAll();
//        assertEquals(findAllResult.size(), chapterList.size());
//        /*for (final ChapterDto chapterDto : chapterList) {
//            assertEquals(chapterEntity.getId(), chapterDto.getId());
//            assertEquals(chapterEntity.getName(), chapterDto.getName());
//            assertEquals(chapterEntity.getText(), chapterDto.getText());
//            assertEquals(chapterEntity.getNumber(), chapterDto.getNumber());
//            assertEquals(chapterEntity.getBook(), chapterDto.getBook());
//        }*/
//    }

//    @Test
//    public void deleteChapter() {
//        doNothing().when(chapterRepository).deleteById(1L);
//        chapterService.deleteChapter(1L, 1L);
//        verify(chapterRepository, times(1)).deleteByBookIdAndNumber(1L, 1L);
//    }

    @Test
    public void getChapterByBookIdAndNumber() {
        final ChapterEntity chapterEntity = MockDataChapter.chapterDbo();
        doReturn(chapterEntity).when(chapterRepository).findByBookIdAndId(1L, 1L);
        final ChapterDto chapterDto = chapterService.getChapterByBookIdAndChapterId(1L, 1L);
        verify(chapterRepository, times(1)).findByBookIdAndId(1L, 1L);
    }

    @Test
    public void updateChapter() {
        final ChapterEntity chapterEntity = MockDataChapter.chapterDbo();
        doReturn(chapterEntity).when(chapterRepository).findByBookIdAndId(1L, 2L);

        final ChapterDto chapterDto = new ChapterDto();
        chapterDto.setNumber(1L);
        chapterDto.setName("booook1");
        chapterDto.setText("text");
        chapterDto.setBookId(10L);
        doReturn(chapterEntity).when(chapterRepository).save(any(ChapterEntity.class));

        chapterService.updateChapter(1L, chapterDto);
        verify(chapterRepository, times(1)).save(any(ChapterEntity.class));
    }
}