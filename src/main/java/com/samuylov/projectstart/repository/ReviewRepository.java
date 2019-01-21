package com.samuylov.projectstart.repository;

import com.samuylov.projectstart.entity.ReviewEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ReviewRepository extends NamedEntityRepository<ReviewEntity> {

    List<ReviewEntity> findAllByBookIdOrderByDateDesc(Long bookId);

    ReviewEntity findFirstById(Long reviewId);

    @Transactional
    void deleteAllByBookId(Long bookId);
}
