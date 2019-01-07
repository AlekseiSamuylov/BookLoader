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

    @PutMapping("/list/{bookId}/update")
    public ResponseEntity updateBook(@PathVariable final Long bookId, @RequestBody final BookDto bookDto) {
        bookService.updateBook(bookId, bookDto);
        return new ResponseEntity<>("Book updated", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity deleteBook(@PathVariable final Long bookId) {
        bookService.deleteBook(bookId);
        return new ResponseEntity<>("Book deleted.", HttpStatus.OK);
    }

    @GetMapping("/byId")
    public ResponseEntity getBookById(@RequestParam final Long id) {
        BookDto bookDto = bookService.getBookById(id);
        return new ResponseEntity<>(bookDto, HttpStatus.FOUND);
    }

    @GetMapping("/list")
    public ResponseEntity getAllBooks(@RequestParam(name = "sort", required = false) final String sortTypeParam) {
        if (sortTypeParam == null) {
            return new ResponseEntity<>(bookService.getBooksList(), HttpStatus.FOUND);
        } else {
            SortType sortType = SortType.valueOf(sortTypeParam.toUpperCase());
            return new ResponseEntity<>(bookService.getBooksList(sortType), HttpStatus.FOUND);
        }
    }

    @PutMapping("/{bookId}/incrRating")
    public ResponseEntity incrementRating(@PathVariable final Long bookId) {
        bookService.incrementRating(bookId);
        return new ResponseEntity<>("Book rating up", HttpStatus.OK);
    }

    @PutMapping("/{bookId}/decrRating")
    public ResponseEntity decrementRating(@PathVariable final Long bookId) {
        bookService.decrementRating(bookId);
        return new ResponseEntity<>("Book rating down.", HttpStatus.OK);
    }
}

