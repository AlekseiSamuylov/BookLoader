package com.samuylov.projectstart.repository;

import com.samuylov.projectstart.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
