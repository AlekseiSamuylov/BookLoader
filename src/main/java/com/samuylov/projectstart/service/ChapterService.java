package com.samuylov.projectstart.service;

import com.samuylov.projectstart.converter.ChapterConverter;
import com.samuylov.projectstart.dto.ChapterDto;
import com.samuylov.projectstart.entity.ChapterEntity;
import com.samuylov.projectstart.repository.ChapterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ChapterService {

    private final ChapterRepository chapterRepository;
    private final ChapterConverter chapterConverter;

    public void createChapter(final ChapterDto chapterDto) {
        chapterRepository.save(chapterConverter.convertToEntity(chapterDto));
    }

    public void updateChapter(final Long chapterId, final ChapterDto chapterDto) {
        final ChapterEntity oldChapter = chapterRepository.findByBookIdAndId(chapterDto.getBookId(), chapterId);

        final ChapterEntity chapterEntity = chapterConverter.convertToEntity(chapterDto);
        oldChapter.setName(chapterEntity.getName());
        oldChapter.setText(chapterEntity.getText());
        oldChapter.setNumber(chapterEntity.getNumber());
        chapterRepository.save(oldChapter);
    }

    public void deleteChapter(final Long chapterId) {
        chapterRepository.deleteById(chapterId);
    }

    public ChapterDto getChapterByBookIdAndChapterId(final Long bookId, final Long chapterNumber) {
        return chapterConverter.convertToDto(chapterRepository.findByBookIdAndId(bookId, chapterNumber));
    }

    public List<ChapterDto> getAllByBookId(final Long bookId) {
        return chapterRepository.findAllByBookId(bookId).stream().map(chapterConverter::convertToDto).collect(Collectors.toList());
    }

    public void deleteAllChaptersByBookId(final Long bookId) {
        chapterRepository.deleteAllByBookId(bookId);
    }
}