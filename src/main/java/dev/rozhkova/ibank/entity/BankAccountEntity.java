package dev.rozhkova.ibank.entity;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "bank_account")
public class BankAccountEntity extends BaseEntity{
  @NotNull
  @Column(name = "account_number")
  private String accountNumber;

  @NotNull
  @Column(name = "money_amount")
  private Double moneyAmount;

  @ManyToOne
  @JoinColumn(name = "user")
  private UserEntity user;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "bankAccount")
  @JsonIgnore
  private List<BankCardEntity> bankCardEntity;

  private Boolean enabled;
}
