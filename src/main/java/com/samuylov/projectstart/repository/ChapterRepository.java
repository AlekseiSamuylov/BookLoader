package com.samuylov.projectstart.repository;

import com.samuylov.projectstart.entity.ChapterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapterRepository extends JpaRepository<ChapterEntity, Long> {
    ChapterEntity findByNumber(Long number);

    ChapterEntity findByBookIdAndNumber(Long bookId, Long number);

    List<ChapterEntity> findAllByBookId(Long bookId);
}