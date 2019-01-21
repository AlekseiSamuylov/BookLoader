package com.samuylov.projectstart.repository;

import com.samuylov.projectstart.entity.CommentEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CommentRepository extends NamedEntityRepository<CommentEntity> {

    List<CommentEntity> findAllByBookIdOrderByDateDesc(Long bookId);

    CommentEntity findFirstById(Long commentId);

    @Transactional
    void deleteAllByBookId(Long bookId);
}
