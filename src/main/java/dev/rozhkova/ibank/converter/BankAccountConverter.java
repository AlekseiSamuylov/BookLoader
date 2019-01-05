package dev.rozhkova.ibank.converter;

import dev.rozhkova.ibank.dto.BankCardDto;
import dev.rozhkova.ibank.dto.UserDto;
import dev.rozhkova.ibank.entity.BankAccountEntity;
import dev.rozhkova.ibank.dto.BankAccountDto;
import dev.rozhkova.ibank.entity.BankCardEntity;
import dev.rozhkova.ibank.entity.UserEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankAccountConverter implements DtoDboConverter<BankAccountDto, BankAccountEntity> {
    private final UserConverter userConverter;
    private final BankCardConverter bankCardConverter;

    @Autowired
    public BankAccountConverter(final UserConverter userConverter,
                                final BankCardConverter bankCardConverter) {
        this.userConverter = userConverter;
        this.bankCardConverter = bankCardConverter;
    }

    @Override
    public BankAccountDto convertToDto(BankAccountEntity dbo) {
        final BankAccountDto bankAccountDto = new BankAccountDto();
        List<BankCardDto> bankCardDtos = bankCardConverter.convertToDto(dbo.getBankCardEntity());
        UserDto userDto = userConverter.convertToDto(dbo.getUser());

        bankAccountDto.setMoneyAmount(dbo.getMoneyAmount());
        bankAccountDto.setUser(userDto);
        bankAccountDto.setAccountNumber(dbo.getAccountNumber());
        bankAccountDto.setBankCardDto(bankCardDtos);
        bankAccountDto.setEnabled(dbo.getEnabled());

        return bankAccountDto;
    }

    @Override
    public BankAccountEntity convertToDbo(BankAccountDto dto) {
        final BankAccountEntity bankAccountEntity = new BankAccountEntity();
        List<BankCardEntity> bankCardEntities = bankCardConverter.convertToDbo(dto.getBankCardDto());
        UserEntity userEntity = userConverter.convertToDbo(dto.getUser());

        bankAccountEntity.setMoneyAmount(dto.getMoneyAmount());
        bankAccountEntity.setUser(userEntity);
        bankAccountEntity.setAccountNumber(dto.getAccountNumber());
        bankAccountEntity.setBankCardEntity(bankCardEntities);
        bankAccountEntity.setEnabled(dto.getEnabled());

        return bankAccountEntity;
    }

    public List<BankAccountEntity> convertToDbo(final List<BankAccountDto> dto) {
        final List<BankAccountEntity> bankAccountEntities = new ArrayList<>();
        dto.forEach(bankAccountDto -> bankAccountEntities.add(convertToDbo(bankAccountDto)));
        return bankAccountEntities;
    }

    public List<BankAccountDto> convertToDto(final List<BankAccountEntity> dbo) {
        final List<BankAccountDto> bankAccountDtos = new ArrayList<>();
        dbo.forEach(bankAccountEntity -> bankAccountDtos.add(convertToDto(bankAccountEntity)));
        return bankAccountDtos;
    }
}