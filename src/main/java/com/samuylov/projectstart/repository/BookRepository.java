package com.samuylov.projectstart.repository;

import com.samuylov.projectstart.entity.BookEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends NamedEntityRepository<BookEntity> {

    BookEntity findFirstById(final Long bookId);
}
