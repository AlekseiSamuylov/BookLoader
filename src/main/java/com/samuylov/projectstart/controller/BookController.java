package com.samuylov.projectstart.controller;

import com.samuylov.projectstart.dto.BookDto;
import com.samuylov.projectstart.dto.ChapterDto;
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
    public List<BookDto> getAllBooks() {
        return bookService.getBooksList();
    }

    @GetMapping("/list/{id}")
    public BookDto getBook(@PathVariable Long id) {
        return bookService.getBook(id);
    }

    @PutMapping("/list/{bookId}/update")
    public String updateBook(@PathVariable Long bookId, @RequestBody final BookDto bookDto) {
        bookService.updateBook(bookId, bookDto);
        return "Book updated";
    }
}
