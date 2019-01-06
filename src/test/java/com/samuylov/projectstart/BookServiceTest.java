package com.samuylov.projectstart;

import com.samuylov.projectstart.entity.BookEntity;
import com.samuylov.projectstart.repository.BookRepository;
import com.samuylov.projectstart.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class BookServiceTest {
    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    public void testGetBookById() {
        final BookEntity bookEntity = new BookEntity();
        bookEntity.setId(1L);
        bookEntity.setName("book");
        bookEntity.setDescription("desc");
        bookEntity.setRating(5);
        when(bookRepository.findById(anyLong())).thenReturn(bookEntity);
    }
}
