package com.samuylov.projectstart.repository;

import com.samuylov.projectstart.entity.BookEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    BookEntity findFirstById(final Long bookId);
    List<BookEntity> findByNameContaining(final String name, final Pageable page);
}
