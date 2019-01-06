package com.samuylov.projectstart.controller;

import com.samuylov.projectstart.dto.BookDto;
import com.samuylov.projectstart.enumeration.SortType;
import com.samuylov.projectstart.service.BookService;
import com.samuylov.projectstart.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;
    private final ChapterService chapterService;

    @Autowired
    public BookController(final BookService bookService, final ChapterService chapterService) {
        this.bookService = bookService;
        this.chapterService = chapterService;
    }

    @PostMapping("/create")
    public String createBook(@RequestBody final BookDto bookDto) {
        bookService.createBook(bookDto);
        return "Book created";
    }

    @GetMapping("/list")
    public List<BookDto> getAllBooks(final @RequestParam(name = "sort", required = false) String sortTypeParam) {
        if (sortTypeParam == null) {
            return bookService.getBooksList();
        } else {
            SortType sortType = SortType.valueOf(sortTypeParam.toUpperCase());
            return bookService.getBooksList(sortType);
        }
    }

    @GetMapping("/byId")
    public BookDto getBokById(final @RequestParam long id) {
        return bookService.getBookById(id);
    }

    @PutMapping("/list/{bookId}/update")
    public String updateBook(@PathVariable Long bookId, @RequestBody final BookDto bookDto) {
        bookService.updateBook(bookId, bookDto);
        return "Book updated";
    }

    @PutMapping("/incrRating")
    public void incrementRating(final @RequestParam long bookId) {
        bookService.incrementRating(bookId);
    }

    @PutMapping("/decrRating")
    public void decrementRating(final @RequestParam long bookId) {
        bookService.decrementRating(bookId);
    }
}

