package dev.rozhkova.ibank.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BankCardDto extends BaseDto {
    private String cardNumber;
    private String cardHolderName;
    private String date;
    private int cvv;
    private BankAccountDto bankAccount;
    private Boolean enabled;
}