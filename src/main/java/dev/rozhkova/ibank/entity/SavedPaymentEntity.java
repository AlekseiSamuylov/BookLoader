package dev.rozhkova.ibank.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "saved_payment")
public class SavedPaymentEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user")
    private UserEntity user;
    @NotNull
    @Column(name = "money_amount")
    private Double moneyAmount;
    @NotNull
    @Column(name = "payment_account")
    @Fetch(value = FetchMode.SUBSELECT)
    private String paymentAccount;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "payment_operation")
    private PaymentOperationEntity paymentOperation;
}
