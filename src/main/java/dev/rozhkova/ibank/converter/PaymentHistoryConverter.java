package dev.rozhkova.ibank.converter;

import dev.rozhkova.ibank.dto.PaymentHistoryDto;
import dev.rozhkova.ibank.entity.PaymentHistoryEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentHistoryConverter implements DtoDboConverter<PaymentHistoryDto, PaymentHistoryEntity> {
    @Override
    public PaymentHistoryDto convertToDto(PaymentHistoryEntity dbo) {
        final PaymentHistoryDto paymentHistoryDto = new PaymentHistoryDto();
        BeanUtils.copyProperties(dbo, paymentHistoryDto);
        return paymentHistoryDto;
    }

    @Override
    public PaymentHistoryEntity convertToDbo(PaymentHistoryDto dto) {
        final PaymentHistoryEntity paymentHistoryEntity = new PaymentHistoryEntity();
        BeanUtils.copyProperties(dto, paymentHistoryEntity);
        return paymentHistoryEntity;
    }

    public List<PaymentHistoryDto> convertToDto(final List<PaymentHistoryEntity> dbo) {
        final List<PaymentHistoryDto> paymentHistoryDtos = new ArrayList<>();
        dbo.forEach(paymentHistoryEntity -> paymentHistoryDtos.add(convertToDto(paymentHistoryEntity)));
        return paymentHistoryDtos;
    }

    public List<PaymentHistoryEntity> convertToDbo(final List<PaymentHistoryDto> dto) {
        final List<PaymentHistoryEntity> paymentHistoryEntities = new ArrayList<>();
        dto.forEach(paymentHistoryDto -> paymentHistoryEntities.add(convertToDbo(paymentHistoryDto)));
        return paymentHistoryEntities;
    }
}