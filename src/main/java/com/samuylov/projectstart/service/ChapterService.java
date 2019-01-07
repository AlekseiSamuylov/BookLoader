package com.samuylov.projectstart.service;

import com.samuylov.projectstart.converter.ChapterConverter;
import com.samuylov.projectstart.dto.ChapterDto;
import com.samuylov.projectstart.entity.ChapterEntity;
import com.samuylov.projectstart.repository.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    public void deleteChapter(Long bookId, Long chapterNumber) {
        ChapterEntity chapterEntity = chapterRepository.findByBookIdAndNumber(bookId, chapterNumber);
        chapterRepository.deleteById(chapterEntity.getId());
    }

    public ChapterDto  getChapterByBookIdAndNumber(Long bookId, Long chapterNumber) {
        return chapterConverter.convertToDto(chapterRepository.findByBookIdAndNumber(bookId, chapterNumber));
    }

    public void updateChapter(Long chapterNumber, ChapterDto chapterDto) {
        ChapterEntity oldChapter = chapterRepository.findByBookIdAndNumber(chapterDto.getBookId(), chapterNumber);
        ChapterEntity chapterEntity = chapterConverter.convertToEntity(chapterDto);
        if (!chapterEntity.getName().equals(oldChapter.getName())) {
            oldChapter.setName(chapterEntity.getName());
        }
        if (!chapterEntity.getText().equals(oldChapter.getText())) {
            oldChapter.setText(chapterEntity.getText());
        }
        chapterRepository.save(oldChapter);
    }
}