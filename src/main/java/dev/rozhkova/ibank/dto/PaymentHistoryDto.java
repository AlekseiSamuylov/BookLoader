package dev.rozhkova.ibank.dto;

import dev.rozhkova.ibank.entity.PaymentOperationEntity;
import dev.rozhkova.ibank.entity.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
public class PaymentHistoryDto extends BaseDto{
    private UserDto user;
    private Date dateOperation;
    private Double moneyAmount;
    private String paymentAccount;
    private PaymentOperationDto paymentOperation;
}