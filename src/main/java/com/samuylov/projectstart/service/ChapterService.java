package com.samuylov.projectstart.service;

import com.samuylov.projectstart.converter.ChapterConverter;
import com.samuylov.projectstart.dto.ChapterDto;
import com.samuylov.projectstart.entity.ChapterEntity;
import com.samuylov.projectstart.repository.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChapterService {
    private final ChapterRepository chapterRepository;
    private final ChapterConverter chapterConverter;

    @Autowired
    public ChapterService(final ChapterRepository chapterRepository, final ChapterConverter chapterConverter) {
        this.chapterRepository = chapterRepository;
        this.chapterConverter = chapterConverter;
    }

    public void createChapter(final ChapterDto chapterDto) {
        chapterRepository.save(chapterConverter.convertToEntity(chapterDto));
    }

    public List<ChapterDto> getChapterList() {
        //return chapterRepository.findAll().stream().map(chapterConverter::convertToDto).collect(Collectors.toList());
        List<ChapterEntity> chapterEntities = chapterRepository.findAll();
        List<ChapterDto> chapterDtos = new ArrayList<>();
        for (ChapterEntity chapterEntity : chapterEntities) {
            chapterDtos.add(chapterConverter.convertToDto(chapterEntity));
        }
        return chapterDtos;
    }

    public void deleteChapter(Long bookId, Long chapterNumber) {
        chapterRepository.deleteByBookIdAndNumber(bookId, chapterNumber);
    }

    public ChapterDto  getChapterByBookIdAndNumber(Long bookId, Long chapterNumber) {
        return chapterConverter.convertToDto(chapterRepository.findByBookIdAndNumber(bookId, chapterNumber));
    }

    public void updateChapter(Long bookId, Long chapterNumber, ChapterDto chapterDto) {
        ChapterEntity oldChapter = chapterRepository.findByBookIdAndNumber(bookId, chapterNumber);
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