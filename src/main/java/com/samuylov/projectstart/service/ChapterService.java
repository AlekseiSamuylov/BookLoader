package com.samuylov.projectstart.service;

import com.samuylov.projectstart.converter.ChapterConverter;
import com.samuylov.projectstart.dto.ChapterDto;
import com.samuylov.projectstart.entity.ChapterEntity;
import com.samuylov.projectstart.repository.ChapterRepository;
import com.vaadin.data.provider.Query;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class ChapterService extends AbstractService<ChapterDto> implements FindElementsService<ChapterDto> {

    private final ChapterRepository chapterRepository;
    private final ChapterConverter chapterConverter;

    @Override
    public void create(final ChapterDto chapterDto) {
        chapterRepository.save(chapterConverter.convertToEntity(chapterDto));
    }

    @Override
    public void update(final Long chapterId, final ChapterDto chapterDto) {
        final ChapterEntity oldChapter = chapterRepository.findByBookIdAndId(chapterDto.getBookId(), chapterId);

        final ChapterEntity chapterEntity = chapterConverter.convertToEntity(chapterDto);
        oldChapter.setName(chapterEntity.getName());
        oldChapter.setText(chapterEntity.getText());
        oldChapter.setNumber(chapterEntity.getNumber());
        chapterRepository.save(oldChapter);
    }

    @Override
    public void delete(final Long chapterId) {
        chapterRepository.deleteById(chapterId);
    }

    @Override
    public List<ChapterDto> getList() {
        return chapterRepository.findAll().stream().map(chapterConverter::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<ChapterDto> getAllByBookId(final Long bookId) {
        return chapterRepository.findAllByBookId(bookId).stream().map(chapterConverter::convertToDto).collect(Collectors.toList());
    }

    public ChapterDto getChapterByBookIdAndChapterId(final Long bookId, final Long chapterNumber) {
        return chapterConverter.convertToDto(chapterRepository.findByBookIdAndId(bookId, chapterNumber));
    }

    public void deleteAllChaptersByBookId(final Long bookId) {
        chapterRepository.deleteAllByBookId(bookId);
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

    @Override
    public Stream<ChapterDto> findWithPagination(final Query<ChapterDto, String> query) {
        final PageRequest pageRequest = preparePageRequest(query);
        final List<ChapterDto> items = chapterRepository.findAll(pageRequest)
                .stream().map(chapterConverter::convertToDto).collect(Collectors.toList());
        return items.stream();
    }
}