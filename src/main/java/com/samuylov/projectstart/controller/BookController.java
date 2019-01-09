package com.samuylov.projectstart.controller;

import com.samuylov.projectstart.dto.BookDto;
import com.samuylov.projectstart.enumeration.SortType;
import com.samuylov.projectstart.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Api(tags = "All requests for work with books", description = "This request will help u make all of u want with books")
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @ApiOperation(tags = "Create requests", value = "Create book by name and description",
            notes = "Beautiful create book")
    @PostMapping("/create")
    public ResponseEntity createBook(@RequestBody final BookDto bookDto) {
        bookService.createBook(bookDto);
        return new ResponseEntity<>("Book created.", HttpStatus.CREATED);
    }

    @ApiOperation(tags = "Update requests", value = "Update book by book id and old book params",
            notes = "Beautiful update book")
    @PutMapping("/list/{bookId}/update")
    public ResponseEntity updateBook(@PathVariable final Long bookId, @RequestBody final BookDto bookDto) {
        bookService.updateBook(bookId, bookDto);
        return new ResponseEntity<>("Book updated", HttpStatus.OK);
    }

    @ApiOperation(tags = "Delete requests", value = "Delete book by book id",
            notes = "Beautiful delete book")
    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity deleteBook(@PathVariable final Long bookId) {
        bookService.deleteBook(bookId);
        return new ResponseEntity<>("Book deleted.", HttpStatus.OK);
    }

    @ApiOperation(tags = "Get element(s) requests", value = "Get one book by book id",
            notes = "Beautiful get book")
    @GetMapping("/byId")
    public ResponseEntity getBookById(@RequestParam final Long id) {
        BookDto bookDto = bookService.getBookById(id);
        return new ResponseEntity<>(bookDto, HttpStatus.FOUND);
    }

    @ApiOperation(tags = "Get element(s) requests",
            value = "Get all books with not required request param [sort]", notes = "Beautiful get books")
    @GetMapping("/list")
    public ResponseEntity getAllBooks(@RequestParam(name = "sort", required = false) final String sortTypeParam) {
        if (sortTypeParam == null) {
            return new ResponseEntity<>(bookService.getBooksList(), HttpStatus.FOUND);
        } else {
            SortType sortType = SortType.valueOf(sortTypeParam.toUpperCase());
            return new ResponseEntity<>(bookService.getBooksList(sortType), HttpStatus.FOUND);
        }
    }

    @ApiOperation(tags = "Special requests", value = "Increment book rating by 1 by book id",
            notes = "Beautiful increment book rating")
    @PutMapping("/{bookId}/incrRating")
    public ResponseEntity incrementRating(@PathVariable final Long bookId) {
        bookService.incrementRating(bookId);
        return new ResponseEntity<>("Book rating up", HttpStatus.OK);
    }

    @ApiOperation(tags = "Special requests", value = "Decrement book rating by 1 by book id",
            notes = "Beautiful decrement book rating")
    @PutMapping("/{bookId}/decrRating")
    public ResponseEntity decrementRating(@PathVariable final Long bookId) {
        bookService.decrementRating(bookId);
        return new ResponseEntity<>("Book rating down.", HttpStatus.OK);
    }
}

