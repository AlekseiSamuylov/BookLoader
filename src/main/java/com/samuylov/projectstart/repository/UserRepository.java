package com.samuylov.projectstart.repository;

import com.samuylov.projectstart.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends NamedEntityRepository<UserEntity> {

    UserEntity findFirstById(final Long userId);

    Optional<UserEntity> findByName(final String name);
}
