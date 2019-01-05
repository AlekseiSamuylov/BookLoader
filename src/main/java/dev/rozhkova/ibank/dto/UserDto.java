package dev.rozhkova.ibank.dto;

import dev.rozhkova.ibank.entity.BankAccountEntity;
import dev.rozhkova.ibank.entity.PaymentHistoryEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDto extends BaseDto{
    private String firstName;
    private String lastName;
    private String patronymic;
    private String passportNumber;
    private String email;
    private String login;
    private String password;
    private Boolean enabled;
    private List<BankAccountDto> bankAccount = new ArrayList<>();
    private List<PaymentHistoryDto> paymentHistory = new ArrayList<>();
    private List<SavedPaymentDto> savedPayments = new ArrayList<>();
}