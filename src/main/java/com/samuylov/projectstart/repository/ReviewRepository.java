package com.samuylov.projectstart.repository;

import com.samuylov.projectstart.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    List<ReviewEntity> findAllByBookIdOrderByDateDesc(long bookId);
}
