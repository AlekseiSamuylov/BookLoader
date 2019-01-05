package dev.rozhkova.ibank.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SavedPaymentDto extends BaseDto{
    private UserDto user;
    private Double moneyAmount;
    private String paymentAccount;
    private PaymentOperationDto paymentOperation;
}
