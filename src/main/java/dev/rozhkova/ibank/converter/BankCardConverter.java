package dev.rozhkova.ibank.converter;

import dev.rozhkova.ibank.dto.BankCardDto;
import dev.rozhkova.ibank.entity.BankCardEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankCardConverter implements DtoDboConverter<BankCardDto, BankCardEntity> {
    @Override
    public BankCardDto convertToDto(BankCardEntity dbo) {
        final BankCardDto bankCardDto = new BankCardDto();
        BeanUtils.copyProperties(dbo, bankCardDto);
        return bankCardDto;
    }

    @Override
    public BankCardEntity convertToDbo(BankCardDto dto) {
        final BankCardEntity bankCardEntity = new BankCardEntity();
        BeanUtils.copyProperties(dto, bankCardEntity);
        return bankCardEntity;
    }

    public List<BankCardDto> convertToDto(final List<BankCardEntity> dbo) {
        final List<BankCardDto> bankCardDtos = new ArrayList<>();
        dbo.forEach(bankCardEntity -> bankCardDtos.add(convertToDto(bankCardEntity)));
        return bankCardDtos;
    }

    public List<BankCardEntity> convertToDbo(final List<BankCardDto> dto) {
        final List<BankCardEntity> bankCardEntities = new ArrayList<>();
        dto.forEach(bankCardDto -> bankCardEntities.add(convertToDbo(bankCardDto)));
        return bankCardEntities;
    }
}