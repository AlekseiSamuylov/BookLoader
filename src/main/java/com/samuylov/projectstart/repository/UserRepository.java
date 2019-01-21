package com.samuylov.projectstart.repository;

import com.samuylov.projectstart.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends NamedEntityRepository<UserEntity> {

    UserEntity findFirstById(final Long userId);
}
