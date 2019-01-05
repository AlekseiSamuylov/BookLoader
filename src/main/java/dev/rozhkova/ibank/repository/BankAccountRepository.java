package dev.rozhkova.ibank.repository;

import dev.rozhkova.ibank.entity.BankAccountEntity;
import dev.rozhkova.ibank.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccountEntity, Long> {
    List<BankAccountEntity> findByUser(UserEntity user);
    BankAccountEntity findByUserAndId(UserEntity user, Long id);
}