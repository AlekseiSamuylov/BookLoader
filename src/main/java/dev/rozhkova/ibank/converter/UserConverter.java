package dev.rozhkova.ibank.converter;

import dev.rozhkova.ibank.dto.BankAccountDto;
import dev.rozhkova.ibank.dto.PaymentHistoryDto;
import dev.rozhkova.ibank.dto.SavedPaymentDto;
import dev.rozhkova.ibank.entity.BankAccountEntity;
import dev.rozhkova.ibank.entity.PaymentHistoryEntity;
import dev.rozhkova.ibank.entity.SavedPaymentEntity;
import dev.rozhkova.ibank.entity.UserEntity;
import dev.rozhkova.ibank.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserConverter implements DtoDboConverter<UserDto, UserEntity> {
    private final BankAccountConverter bankAccountConverter;
    private final PaymentHistoryConverter paymentHistoryConverter;
    private final SavedPaymentConverter savedPaymentConverter;

    @Autowired
    public UserConverter(final BankAccountConverter bankAccountConverter,
                         final PaymentHistoryConverter paymentHistoryConverter,
                         final SavedPaymentConverter savedPaymentConverter) {
        this.bankAccountConverter = bankAccountConverter;
        this.paymentHistoryConverter = paymentHistoryConverter;
        this.savedPaymentConverter = savedPaymentConverter;
    }

    @Override
    public UserDto convertToDto(UserEntity dbo) {
        final UserDto userDto = new UserDto();
        List<BankAccountDto> bankAccountDtos = bankAccountConverter.convertToDto(dbo.getBankAccount());
        List<PaymentHistoryDto> paymentHistoryDtos = paymentHistoryConverter.convertToDto(dbo.getPaymentHistory());
        List<SavedPaymentDto> savedPaymentDtos = savedPaymentConverter.convertToDto(dbo.getSavedPayment());

        userDto.setFirstName(dbo.getFirstName());
        userDto.setLastName(dbo.getLastName());
        userDto.setPatronymic(dbo.getPatronymic());
        userDto.setPassportNumber(dbo.getPassportNumber());
        userDto.setEmail(dbo.getEmail());
        userDto.setLogin(dbo.getLogin());
        userDto.setPassword(dbo.getPassword());
        userDto.setEnabled(dbo.getEnabled());
        userDto.setBankAccount(bankAccountDtos);
        userDto.setPaymentHistory(paymentHistoryDtos);
        userDto.setSavedPayments(savedPaymentDtos);

        return userDto;
    }

    @Override
    public UserEntity convertToDbo(UserDto dto) {
        final UserEntity userEntity = new UserEntity();
        List<BankAccountEntity> bankAccountEntities = bankAccountConverter.convertToDbo(dto.getBankAccount());
        List<PaymentHistoryEntity> paymentHistoryEntities = paymentHistoryConverter.convertToDbo(dto.getPaymentHistory());
        List<SavedPaymentEntity> savedPaymentEntities = savedPaymentConverter.convertToDbo(dto.getSavedPayments());

        userEntity.setFirstName(dto.getFirstName());
        userEntity.setLastName(dto.getLastName());
        userEntity.setPatronymic(dto.getPatronymic());
        userEntity.setPassportNumber(dto.getPassportNumber());
        userEntity.setEmail(dto.getEmail());
        userEntity.setLogin(dto.getLogin());
        userEntity.setPassword(dto.getPassword());
        userEntity.setEnabled(dto.getEnabled());
        userEntity.setBankAccount(bankAccountEntities);
        userEntity.setPaymentHistory(paymentHistoryEntities);
        userEntity.setSavedPayment(savedPaymentEntities);

        return userEntity;
    }
}