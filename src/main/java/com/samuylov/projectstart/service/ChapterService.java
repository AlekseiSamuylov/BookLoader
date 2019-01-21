package com.samuylov.projectstart.service;

import com.samuylov.projectstart.converter.DtoEntityConverter;
import com.samuylov.projectstart.dto.ChapterDto;
import com.samuylov.projectstart.entity.ChapterEntity;
import com.samuylov.projectstart.repository.ChapterRepository;
import com.samuylov.projectstart.repository.NamedEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChapterService extends AbstractService<ChapterDto, ChapterEntity> implements FindElementsService<ChapterDto> {

    @Autowired
    public ChapterService(final NamedEntityRepository<ChapterEntity> repository,
                          final DtoEntityConverter<ChapterDto, ChapterEntity> converter) {
        super(repository, converter);
    }

    @Override
    public void create(final ChapterDto chapterDto) {
        repository.save(converter.convertToEntity(chapterDto));
    }

    @Override
    public void update(final Long chapterId, final ChapterDto chapterDto) {
        final ChapterEntity oldChapter = ((ChapterRepository) repository).findByBookIdAndId(chapterDto.getBookId(), chapterId);

        final ChapterEntity chapterEntity = converter.convertToEntity(chapterDto);
        oldChapter.setName(chapterEntity.getName());
        oldChapter.setText(chapterEntity.getText());
        oldChapter.setNumber(chapterEntity.getNumber());
        repository.save(oldChapter);
    }

    @Override
    public void save(final ChapterDto chapterDto) {
        if (chapterDto.getId() == null){
            create(chapterDto);
        } else {
            update(chapterDto.getId(), chapterDto);
        }
    }

    @Override
    public void delete(final Long chapterId) {
        repository.deleteById(chapterId);
    }

    @Override
    public List<ChapterDto> getList() {
        return repository.findAll().stream().map(converter::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<ChapterDto> getAllByBookId(final Long bookId) {
        return ((ChapterRepository) repository).findAllByBookId(bookId).stream().map(converter::convertToDto).collect(Collectors.toList());
    }

    public ChapterDto getChapterByBookIdAndChapterId(final Long bookId, final Long chapterNumber) {
        return converter.convertToDto(((ChapterRepository) repository).findByBookIdAndId(bookId, chapterNumber));
    }

    public void deleteAllChaptersByBookId(final Long bookId) {
        ((ChapterRepository) repository).deleteAllByBookId(bookId);
    }

    public Long getPrevChapterId(final Long bookId, final Long currentChapterId) {
        List<ChapterDto> chapters = getAllByBookId(bookId);

        int currentChapterPosition = getCurrentChapterPosition(chapters, currentChapterId);

        if (currentChapterPosition > 0) {
            return chapters.get(currentChapterPosition - 1).getId();
        }

        return (long) -1;
    }

    public Long getNextChapterId(final Long bookId, final Long currentChapterId) {
        List<ChapterDto> chapters = getAllByBookId(bookId);

        int currentChapterPosition = getCurrentChapterPosition(chapters, currentChapterId);

        if (currentChapterPosition < chapters.size() - 1) {
            return chapters.get(currentChapterPosition + 1).getId();
        }

        return (long) -1;
    }

    private int getCurrentChapterPosition(final List<ChapterDto> chapters, final Long currentChapterId) {
        int currentChapterPosition = -1;
        for (int i = 0; i < chapters.size(); i++) {
            if (chapters.get(i).getId().equals(currentChapterId)) {
                currentChapterPosition = i;
                break;
            }
        }

        return currentChapterPosition;
    }
}