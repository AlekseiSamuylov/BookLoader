package com.samuylov.projectstart.converter;

import com.samuylov.projectstart.dto.UserDto;
import com.samuylov.projectstart.entity.UserEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserConverter implements DtoEntityConverter<UserDto, UserEntity> {
    @Override
    public UserDto convertToDto(UserEntity entity) {
        final UserDto userDto = new UserDto();
        BeanUtils.copyProperties(entity, userDto);
        return userDto;
    }

    @Override
    public UserEntity convertToEntity(UserDto dto) {
        final UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(dto, userEntity);
        return userEntity;
    }
}
