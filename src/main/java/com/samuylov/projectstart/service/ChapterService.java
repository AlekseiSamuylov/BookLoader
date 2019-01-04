package com.samuylov.projectstart.service;

import com.samuylov.projectstart.converter.ChapterConverter;
import com.samuylov.projectstart.dto.ChapterDto;
import com.samuylov.projectstart.entity.ChapterDbo;
import com.samuylov.projectstart.repository.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ChapterService {
    private final ChapterRepository chapterRepository;
    private final ChapterConverter chapterConverter;

    @Autowired
    public ChapterService(final ChapterRepository chapterRepository, final ChapterConverter chapterConverter) {
        this.chapterRepository = chapterRepository;
        this.chapterConverter = chapterConverter;
    }

    public void createChapter(final ChapterDto chapterDto) {
        chapterRepository.save(chapterConverter.convertToDbo(chapterDto));
    }

    public List<ChapterDto> getChapterList() {
        return chapterRepository.findAll().stream().map(chapterConverter::convertToDto).collect(Collectors.toList());
    }

    public void deleteChapter(Long bookId, Long chapterNumber) {
        chapterRepository.deleteByBookIdAndNumber(bookId, chapterNumber);
    }

    public ChapterDto  getChapterByBookIdAndNumber(Long bookId, Long chapterNumber) {
        return chapterConverter.convertToDto(chapterRepository.findByBookIdAndNumber(bookId, chapterNumber));
    }

    public void updateChapter(Long bookId, Long chapterNumber, ChapterDto chapterDto) {
        ChapterDbo oldChapter = chapterRepository.findByBookIdAndNumber(bookId, chapterNumber);
        ChapterDbo chapterDbo = chapterConverter.convertToDbo(chapterDto);
        if (chapterDbo.getName() != oldChapter.getName()) {
            oldChapter.setName(chapterDbo.getName());
        }
        if (chapterDbo.getText() != oldChapter.getText()) {
            oldChapter.setText(chapterDbo.getText());
        }
        chapterRepository.save(oldChapter);
    }
}