package com.samuylov.projectstart.service;

import com.samuylov.projectstart.converter.BookConverter;
import com.samuylov.projectstart.dto.BookDto;
import com.samuylov.projectstart.dto.ChapterDto;
import com.samuylov.projectstart.dto.CommentDto;
import com.samuylov.projectstart.dto.ReviewDto;
import com.samuylov.projectstart.entity.BookEntity;
import com.samuylov.projectstart.enumeration.SortType;
import com.samuylov.projectstart.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookConverter bookConverter;
    private final CommentService commentService;
    private final ReviewService reviewService;
    private final ChapterService chapterService;
    private final Map<SortType, Sort> sortMap;

    @Autowired
    public BookService(final BookRepository bookRepository, final BookConverter bookConverter,
                       final CommentService commentService, final ReviewService reviewService,
                       @Lazy final ChapterService chapterService, final Map<SortType, Sort> sortMap) {
        this.bookRepository = bookRepository;
        this.bookConverter = bookConverter;
        this.commentService = commentService;
        this.reviewService = reviewService;
        this.chapterService = chapterService;
        this.sortMap = sortMap;
    }

    public void createBook(final BookDto bookDto) {
        bookDto.setRating(0);
        bookRepository.save(bookConverter.convertToEntity(bookDto));
    }

    public List<BookDto> getBooksList() {
        return bookRepository.findAll().stream().map(bookConverter::convertToDto).collect(Collectors.toList());
    }

    public BookDto getBookById(final long bookId) {
        final BookDto bookDto = bookConverter.convertToDto(bookRepository.findById(bookId));
        final List<CommentDto> comments = commentService.getAllByBookId(bookId);
        final List<ReviewDto> reviews = reviewService.getAllByBookId(bookId);
        final List<ChapterDto> chapters = chapterService.getAllByBookId(bookId);
        bookDto.setComments(comments);
        bookDto.setReviews(reviews);
        bookDto.setChapters(chapters);
        return bookDto;
    }

    public void updateBook(Long bookId, BookDto bookDto) {
        BookEntity oldBook  = bookRepository.findById(bookId).orElse(null);
        BookEntity bookDbo = bookConverter.convertToEntity(bookDto);
        if (!bookDbo.getName().equals(oldBook.getName())) {
            oldBook.setName(bookDbo.getName());
        }
        if (!bookDbo.getDescription().equals(oldBook.getDescription())) {
            oldBook.setDescription(bookDbo.getDescription());
        }
        bookRepository.save(oldBook);
    }

    public List<BookDto> getBooksList(SortType sortType) {
        final Sort sort = sortMap.get(sortType);
        return bookRepository.findAll(sort).stream().map(bookConverter::convertToDto).collect(Collectors.toList());
    }

    public void incrementRating(long bookId) {
        final BookDto bookDto = bookConverter.convertToDto(bookRepository.findById(bookId));
        bookDto.incrementRating();
        bookRepository.save(bookConverter.convertToEntity(bookDto));
    }

    public void decrementRating(long bookId) {
        final BookDto bookDto = bookConverter.convertToDto(bookRepository.findById(bookId));
        bookDto.decrementRating();
        bookRepository.save(bookConverter.convertToEntity(bookDto));
    }

    public boolean isBookContains(final Long bookId) {
        BookEntity bookEntity = bookRepository.getOne(bookId);
        return bookEntity != null;
    }
}
