package dev.rozhkova.ibank.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentOperationDto extends BaseDto{
    private String name;
}