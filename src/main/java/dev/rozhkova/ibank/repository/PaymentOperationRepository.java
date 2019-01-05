package dev.rozhkova.ibank.repository;

import dev.rozhkova.ibank.entity.PaymentOperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentOperationRepository extends JpaRepository<PaymentOperationEntity,Long> {
}