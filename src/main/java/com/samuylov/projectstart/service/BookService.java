package com.samuylov.projectstart.service;

import com.samuylov.projectstart.converter.DtoEntityConverter;
import com.samuylov.projectstart.dto.BookDto;
import com.samuylov.projectstart.dto.ChapterDto;
import com.samuylov.projectstart.dto.CommentDto;
import com.samuylov.projectstart.dto.ReviewDto;
import com.samuylov.projectstart.entity.BookEntity;
import com.samuylov.projectstart.enumeration.SortType;
import com.samuylov.projectstart.repository.BookRepository;
import com.samuylov.projectstart.repository.NamedEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookService extends AbstractService<BookDto, BookEntity> {

    private final CommentService commentService;
    private final ReviewService reviewService;
    private final ChapterService chapterService;
    private final Map<SortType, Sort> sortMap;

    @Autowired
    public BookService(final NamedEntityRepository<BookEntity> repository,
                       final DtoEntityConverter<BookDto, BookEntity> converter,
                       final CommentService commentService,
                       final ReviewService reviewService,
                       final ChapterService chapterService,
                       final Map<SortType, Sort> sortMap) {
        super(repository, converter);
        this.commentService = commentService;
        this.reviewService = reviewService;
        this.chapterService = chapterService;
        this.sortMap = sortMap;
    }

    @Override
    public void create(final BookDto bookDto) {
        bookDto.setRating((long) 0);
        repository.save(converter.convertToEntity(bookDto));
    }

    @Override
    public void update(final Long bookId, final BookDto bookDto) {
        final BookEntity oldBook = ((BookRepository) repository).findFirstById(bookId);

        oldBook.setName(bookDto.getName());
        oldBook.setDescription(bookDto.getDescription());
        repository.save(oldBook);
    }

    @Override
    public void save(final BookDto bookDto){
        if (bookDto.getId() == null) {
            create(bookDto);
        } else {
            update(bookDto.getId(), bookDto);
        }
    }

    @Override
    public void delete(final Long bookId) {
        chapterService.deleteAllChaptersByBookId(bookId);
        commentService.deleteAllCommentsByBookId(bookId);
        reviewService.deleteAllReviewsByBookId(bookId);
        repository.deleteById(bookId);
    }

    @Override
    public List<BookDto> getList() {
        return repository.findAll().stream().map(converter::convertToDto).collect(Collectors.toList());
    }

    public BookDto getBookById(final Long bookId) {
        final BookDto bookDto = converter.convertToDto(((BookRepository) repository).findFirstById(bookId));

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
        return repository.findAll(sort).stream().map(converter::convertToDto).collect(Collectors.toList());
    }

    public void incrementRating(final Long bookId) {
        final BookDto bookDto = converter.convertToDto(repository.getOne(bookId));

        bookDto.setRating(bookDto.getRating() + 1);
        repository.save(converter.convertToEntity(bookDto));
    }

    public void decrementRating(final Long bookId) {
        final BookDto bookDto = converter.convertToDto(repository.getOne(bookId));

        bookDto.setRating(bookDto.getRating() - 1);
        repository.save(converter.convertToEntity(bookDto));
    }

    public boolean isContains(final Long bookId) {
        return getBookById(bookId) != null;
    }
}
