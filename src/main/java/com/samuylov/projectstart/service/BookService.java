package com.samuylov.projectstart.service;

import com.samuylov.projectstart.converter.BookConverter;
import com.samuylov.projectstart.dto.BookDto;
import com.samuylov.projectstart.dto.ChapterDto;
import com.samuylov.projectstart.dto.CommentDto;
import com.samuylov.projectstart.dto.ReviewDto;
import com.samuylov.projectstart.entity.BookEntity;
import com.samuylov.projectstart.enumeration.SortType;
import com.samuylov.projectstart.repository.BookRepository;
import com.vaadin.data.provider.Query;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class BookService extends AbstractService<BookDto> {

    private final BookRepository bookRepository;
    private final BookConverter bookConverter;
    private final CommentService commentService;
    private final ReviewService reviewService;
    private final ChapterService chapterService;
    private final Map<SortType, Sort> sortMap;

    @Override
    public void create(final BookDto bookDto) {
        bookDto.setRating(0);
        bookRepository.save(bookConverter.convertToEntity(bookDto));
    }

    @Override
    public void update(final Long bookId, final BookDto bookDto) {
        final BookEntity oldBook = bookRepository.findFirstById(bookId);

        oldBook.setName(bookDto.getName());
        oldBook.setDescription(bookDto.getDescription());
        bookRepository.save(oldBook);
    }

    @Override
    public void delete(final Long bookId) {
        chapterService.deleteAllChaptersByBookId(bookId);
        commentService.deleteAllCommentsByBookId(bookId);
        reviewService.deleteAllReviewsByBookId(bookId);
        bookRepository.deleteById(bookId);
    }

    @Override
    public List<BookDto> getList() {
        return bookRepository.findAll().stream().map(bookConverter::convertToDto).collect(Collectors.toList());
    }

    public BookDto getBookById(final Long bookId) {
        final BookDto bookDto = bookConverter.convertToDto(bookRepository.findFirstById(bookId));

        final List<CommentDto> comments = commentService.getAllByBookId(bookId);
        final List<ReviewDto> reviews = reviewService.getAllByBookId(bookId);
        final List<ChapterDto> chapters = chapterService.getAllByBookId(bookId);
        bookDto.setComments(comments);
        bookDto.setReviews(reviews);
        bookDto.setChapters(chapters);

        return bookDto;
    }

    public List<BookDto> getBooksList(final SortType sortType) {
        final Sort sort = sortMap.get(sortType);
        return bookRepository.findAll(sort).stream().map(bookConverter::convertToDto).collect(Collectors.toList());
    }

    public void incrementRating(final Long bookId) {
        final BookDto bookDto = bookConverter.convertToDto(bookRepository.getOne(bookId));

        bookDto.setRating(bookDto.getRating() + 1);
        bookRepository.save(bookConverter.convertToEntity(bookDto));
    }

    public void decrementRating(final Long bookId) {
        final BookDto bookDto = bookConverter.convertToDto(bookRepository.getOne(bookId));

        bookDto.setRating(bookDto.getRating() - 1);
        bookRepository.save(bookConverter.convertToEntity(bookDto));
    }

    public boolean isContains(final Long bookId) {
        return getBookById(bookId) != null;
    }

    @Override
    public Stream<BookDto> findWithPagination(final Query<BookDto, String> query) {
        final PageRequest pageRequest = preparePageRequest(query);
        if (query.getFilter().isPresent()) {
            final List<BookDto> items = bookRepository.findByNameContaining(query.getFilter().get(), pageRequest)
                    .stream().map(bookConverter::convertToDto).collect(Collectors.toList());
            return items.stream();
        } else {
            final List<BookDto> items = bookRepository.findAll(pageRequest)
                    .stream().map(bookConverter::convertToDto).collect(Collectors.toList());
            return items.stream();
        }
    }
}
