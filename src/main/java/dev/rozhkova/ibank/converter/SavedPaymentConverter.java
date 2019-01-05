package dev.rozhkova.ibank.converter;

import dev.rozhkova.ibank.dto.PaymentOperationDto;
import dev.rozhkova.ibank.dto.SavedPaymentDto;
import dev.rozhkova.ibank.dto.UserDto;
import dev.rozhkova.ibank.entity.PaymentOperationEntity;
import dev.rozhkova.ibank.entity.SavedPaymentEntity;
import dev.rozhkova.ibank.entity.UserEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SavedPaymentConverter implements DtoDboConverter<SavedPaymentDto, SavedPaymentEntity>{
    /*private final UserConverter userConverter;
    private final PaymentOperationConverter paymentOperationConverter;

    @Autowired
    public SavedPaymentConverter(final UserConverter userConverter,
                                 final PaymentOperationConverter paymentOperationConverter) {
        this.userConverter = userConverter;
        this.paymentOperationConverter = paymentOperationConverter;
    }*/

    @Override
    public SavedPaymentDto convertToDto(SavedPaymentEntity dbo) {
        final SavedPaymentDto savedPaymentDto = new SavedPaymentDto();
        BeanUtils.copyProperties(dbo, savedPaymentDto);
        /*
        UserDto userDto = userConverter.convertToDto(dbo.getUser());
        PaymentOperationDto paymentOperationDto =
                paymentOperationConverter.convertToDto(dbo.getPaymentOperation());

        savedPaymentDto.setUser(userDto);
        savedPaymentDto.setMoneyAmount(dbo.getMoneyAmount());
        savedPaymentDto.setPaymentAccount(dbo.getPaymentAccount());
        savedPaymentDto.setPaymentOperation(paymentOperationDto);
*/
        return savedPaymentDto;
    }

    @Override
    public SavedPaymentEntity convertToDbo(SavedPaymentDto dto) {
        final SavedPaymentEntity savedPaymentEntity = new SavedPaymentEntity();
        BeanUtils.copyProperties(dto, savedPaymentEntity);
        /*
        UserEntity userEntity = userConverter.convertToDbo(dto.getUser());
        PaymentOperationEntity paymentOperationEntity =
                paymentOperationConverter.convertToDbo(dto.getPaymentOperation());

        savedPaymentEntity.setUser(userEntity);
        savedPaymentEntity.setMoneyAmount(dto.getMoneyAmount());
        savedPaymentEntity.setPaymentAccount(dto.getPaymentAccount());
        savedPaymentEntity.setPaymentOperation(paymentOperationEntity);
*/
        return savedPaymentEntity;
    }

    public List<SavedPaymentDto> convertToDto(final List<SavedPaymentEntity> dbo) {
        final List<SavedPaymentDto> savedPaymentDtos = new ArrayList<>();
        dbo.forEach(savedPaymentEntity -> savedPaymentDtos.add(convertToDto(savedPaymentEntity)));
        return savedPaymentDtos;
    }

    public List<SavedPaymentEntity> convertToDbo(final List<SavedPaymentDto> dto) {
        final List<SavedPaymentEntity> savedPaymentEntities = new ArrayList<>();
        dto.forEach(savedPaymentDto -> savedPaymentEntities.add(convertToDbo(savedPaymentDto)));
        return savedPaymentEntities;
    }
}
