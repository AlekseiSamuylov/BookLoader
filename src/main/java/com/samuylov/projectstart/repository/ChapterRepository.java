package com.samuylov.projectstart.repository;

import com.samuylov.projectstart.entity.ChapterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterRepository extends JpaRepository<ChapterEntity, Long> {
    ChapterEntity findByNumber(Long number);

    void deleteByBookIdAndNumber(Long bookId, Long number);

    ChapterEntity findByBookIdAndNumber(Long bookId, Long number);
}