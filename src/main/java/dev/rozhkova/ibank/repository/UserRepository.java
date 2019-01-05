package dev.rozhkova.ibank.repository;

import dev.rozhkova.ibank.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByLogin(String login);
    UserEntity findByLastName(String lastName);

    /*@Query("update UserEntity u set u.firstName = :firstName where u.id = :id")
    String changeFirstName(@Param("id") Long id,
                           @Param("firstName") String firstName);*/
}