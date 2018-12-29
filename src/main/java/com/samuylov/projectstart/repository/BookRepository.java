package com.samuylov.projectstart.repository;

import com.samuylov.projectstart.entity.BookDbo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookDbo, Long> {
}
