package com.samuylov.projectstart.repository;

import com.samuylov.projectstart.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    List<ReviewEntity> findAllByBookIdOrderByDateDesc(Long bookId);

    @Transactional
    void deleteAllByBookId(Long bookId);
}
