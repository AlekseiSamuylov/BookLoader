package com.samuylov.projectstart.controller;

import com.samuylov.projectstart.dto.BookDto;
import com.samuylov.projectstart.enumeration.SortType;
import com.samuylov.projectstart.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

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
    public BookDto getBookById(final @RequestParam long id) {
        return bookService.getBookById(id);
    }

    @PutMapping("/list/{bookId}/update")
    public String updateBook(@PathVariable Long bookId, @RequestBody final BookDto bookDto) {
        bookService.updateBook(bookId, bookDto);
        return "Book updated";
    }

    @PutMapping("/{bookId}/incrRating")
    public void incrementRating(@PathVariable final long bookId) {
        bookService.incrementRating(bookId);
    }

    @PutMapping("/{bookId}/decrRating")
    public void decrementRating(@PathVariable final long bookId) {
        bookService.decrementRating(bookId);
    }
}

