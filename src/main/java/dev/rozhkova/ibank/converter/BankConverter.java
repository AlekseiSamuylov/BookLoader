package dev.rozhkova.ibank.converter;

import dev.rozhkova.ibank.dto.BankDto;
import dev.rozhkova.ibank.entity.BankEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BankConverter implements DtoDboConverter<BankDto, BankEntity> {
    @Override
    public BankDto convertToDto(BankEntity dbo) {
        final BankDto bankDto = new BankDto();
        BeanUtils.copyProperties(dbo, bankDto);
        return bankDto;
    }

    @Override
    public BankEntity convertToDbo(BankDto dto) {
        final BankEntity bankEntity = new BankEntity();
        BeanUtils.copyProperties(dto, bankEntity);
        return bankEntity;
    }
}