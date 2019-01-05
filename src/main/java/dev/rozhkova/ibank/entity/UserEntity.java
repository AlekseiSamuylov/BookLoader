package dev.rozhkova.ibank.entity;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.rozhkova.ibank.validator.ValidEmail;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "user")
public class UserEntity extends BaseEntity{
  @NotNull
  @Column(name = "first_name")
  private String firstName;

  @NotNull
  @Column(name = "last_name")
  private String lastName;

  private String patronymic;
  @NotNull
  @Column(name = "passport_number", unique = true)
  private String passportNumber;

  @NotNull
  @ValidEmail
  @Column(unique = true)
  private String email;

  @NotNull
  @Column(unique = true)
  private String login;

  @NotNull
  private String password;

  @Column(columnDefinition = "boolean default true")
  private Boolean enabled;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
  @JsonIgnore
  private List<BankAccountEntity> bankAccount = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
  @JsonIgnore
  private List<PaymentHistoryEntity> paymentHistory = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
  @JsonIgnore
  private List<SavedPaymentEntity> savedPayment = new ArrayList<>();
}
