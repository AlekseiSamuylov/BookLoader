package dev.rozhkova.ibank.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "payment_history")
public class PaymentHistoryEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user")
    private UserEntity user;
    @NotNull
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_operation")
    private Date dateOperation;
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