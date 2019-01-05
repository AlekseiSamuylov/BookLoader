package dev.rozhkova.ibank.dto;

import dev.rozhkova.ibank.entity.BankCardEntity;
import dev.rozhkova.ibank.entity.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BankAccountDto extends BaseDto{
    private Double moneyAmount;
    private UserDto user;
    private String accountNumber;
    private List<BankCardDto> bankCardDto;
    private Boolean enabled;
}