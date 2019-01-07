package com.samuylov.projectstart.controller;

import com.samuylov.projectstart.dto.BookDto;
import com.samuylov.projectstart.enumeration.SortType;
import com.samuylov.projectstart.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @PostMapping("/create")
    public ResponseEntity createBook(@RequestBody final BookDto bookDto) {
        bookService.createBook(bookDto);
        return new ResponseEntity<>("Book created.", HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity getAllBooks(final @RequestParam(name = "sort", required = false) String sortTypeParam) {
        if (sortTypeParam == null) {
            return new ResponseEntity<>(bookService.getBooksList(), HttpStatus.FOUND);
        } else {
            SortType sortType = SortType.valueOf(sortTypeParam.toUpperCase());
            return new ResponseEntity<>(bookService.getBooksList(sortType), HttpStatus.FOUND);
        }
    }

    @GetMapping("/byId")
    public ResponseEntity getBookById(final @RequestParam long id) {
        BookDto bookDto = bookService.getBookById(id);
        if (bookDto != null) {
            return new ResponseEntity<>(bookDto, HttpStatus.FOUND);
        }
        return new ResponseEntity<>("Book not found.", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/list/{bookId}/update")
    public ResponseEntity updateBook(@PathVariable Long bookId, @RequestBody final BookDto bookDto) {
        if (bookService.updateBook(bookId, bookDto)) {
            return new ResponseEntity<>("Book updated", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Book not updated.", HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("/{bookId}/incrRating")
    public ResponseEntity incrementRating(@PathVariable final long bookId) {
        if (bookService.incrementRating(bookId)) {
            new ResponseEntity<>("Book rating up", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Book not found.", HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("/{bookId}/decrRating")
    public ResponseEntity decrementRating(@PathVariable final long bookId) {
        if (bookService.decrementRating(bookId)) {
            new ResponseEntity<>("Book rating down.", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Book not found.", HttpStatus.NOT_MODIFIED);
    }
}

