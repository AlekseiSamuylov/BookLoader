package com.samuylov.projectstart.service;

import com.samuylov.projectstart.converter.BookConverter;
import com.samuylov.projectstart.dto.BookDto;
import com.samuylov.projectstart.entity.BookDbo;
import com.samuylov.projectstart.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookConverter bookConverter;

    @Autowired
    public BookService(final BookRepository bookRepository, final BookConverter bookConverter) {
        this.bookRepository = bookRepository;
        this.bookConverter = bookConverter;
    }

    public void createBook(final BookDto bookDto) {
        bookRepository.save(bookConverter.convertToDbo(bookDto));
    }

    public List<BookDto> getBooksList() {
        return bookRepository.findAll().stream().map(bookConverter::convertToDto).collect(Collectors.toList());
    }

    public BookDto getBook(Long id) {
        return bookConverter.convertToDto(bookRepository.findById(id).orElse(new BookDbo()));
    }

    public void updateBook(Long bookId, BookDto bookDto) {
        BookDbo oldBook  = bookRepository.findById(bookId).orElse(null);
        BookDbo bookDbo = bookConverter.convertToDbo(bookDto);
        if (bookDbo.getName() != oldBook.getName()) {
            oldBook.setName(bookDbo.getName());
        }
        if (bookDbo.getDescription() != oldBook.getDescription()) {
            oldBook.setDescription(bookDbo.getDescription());
        }
        bookRepository.save(oldBook);
    }
}
