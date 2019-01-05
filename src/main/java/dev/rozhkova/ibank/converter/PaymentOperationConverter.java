package dev.rozhkova.ibank.converter;

import dev.rozhkova.ibank.dto.PaymentOperationDto;
import dev.rozhkova.ibank.entity.PaymentOperationEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PaymentOperationConverter implements DtoDboConverter<PaymentOperationDto, PaymentOperationEntity> {
    @Override
    public PaymentOperationDto convertToDto(PaymentOperationEntity dbo) {
        final PaymentOperationDto paymentOperationDto = new PaymentOperationDto();
        BeanUtils.copyProperties(dbo, paymentOperationDto);
        return paymentOperationDto;
    }

    @Override
    public PaymentOperationEntity convertToDbo(PaymentOperationDto dto) {
        final PaymentOperationEntity paymentOperationEntity = new PaymentOperationEntity();
        BeanUtils.copyProperties(dto, paymentOperationEntity);
        return paymentOperationEntity;
    }
}