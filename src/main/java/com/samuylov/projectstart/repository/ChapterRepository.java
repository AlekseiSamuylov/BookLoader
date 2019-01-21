package com.samuylov.projectstart.repository;

import com.samuylov.projectstart.entity.ChapterEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ChapterRepository extends NamedEntityRepository<ChapterEntity> {

    ChapterEntity findByBookIdAndId(Long bookId, Long chapterId);

    List<ChapterEntity> findAllByBookId(Long bookId);

    @Transactional
    void deleteAllByBookId(Long bookId);
}