package com.samuylov.projectstart.repository;

import com.samuylov.projectstart.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findFirstById(final Long userId);
}
