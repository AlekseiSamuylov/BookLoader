package com.samuylov.projectstart.service;

import com.samuylov.projectstart.converter.ChapterConverter;
import com.samuylov.projectstart.dto.ChapterDto;
import com.samuylov.projectstart.entity.ChapterEntity;
import com.samuylov.projectstart.repository.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChapterService {

    private final ChapterRepository chapterRepository;
    private final ChapterConverter chapterConverter;
    private final BookService bookService;

    @Autowired
    public ChapterService(final ChapterRepository chapterRepository, final ChapterConverter chapterConverter,
                          @Lazy final BookService bookService) {
        this.chapterRepository = chapterRepository;
        this.chapterConverter = chapterConverter;
        this.bookService = bookService;
    }

    public void createChapter(final ChapterDto chapterDto) {
        if (bookService.isBookContains(chapterDto.getBookId())) {
            chapterRepository.save(chapterConverter.convertToEntity(chapterDto));
        }
    }

    public List<ChapterDto> getAllByBookId(final Long bookId) {
        return chapterRepository.findAllByBookId(bookId).stream().map(chapterConverter::convertToDto).collect(Collectors.toList());
    }

    public void deleteChapter(final Long bookId, final Long chapterId) {
        final ChapterEntity chapterEntity = chapterRepository.findByBookIdAndId(bookId, chapterId);
        chapterRepository.deleteById(chapterEntity.getId());
    }

    public void deleteAllChaptersByBookId(final Long bookId) {
        chapterRepository.deleteAllByBookId(bookId);
    }

    public ChapterDto getChapterByBookIdAndNumber(final Long bookId, final Long chapterNumber) {
        return chapterConverter.convertToDto(chapterRepository.findByBookIdAndId(bookId, chapterNumber));
    }

    public boolean updateChapter(final Long chapterId, final ChapterDto chapterDto) {
        final ChapterEntity oldChapter = chapterRepository.findByBookIdAndId(chapterDto.getBookId(), chapterId);

        if (oldChapter == null) {
            return false;
        }

        final ChapterEntity chapterEntity = chapterConverter.convertToEntity(chapterDto);
        oldChapter.setName(chapterEntity.getName());
        oldChapter.setText(chapterEntity.getText());
        oldChapter.setNumber(chapterEntity.getNumber());

        chapterRepository.save(oldChapter);
        return true;
    }
}