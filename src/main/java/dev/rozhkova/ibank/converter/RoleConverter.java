package dev.rozhkova.ibank.converter;

import dev.rozhkova.ibank.entity.RoleEntity;
import dev.rozhkova.ibank.dto.RoleDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class RoleConverter implements DtoDboConverter<RoleDto, RoleEntity> {
    @Override
    public RoleDto convertToDto(RoleEntity dbo) {
        final RoleDto roleDto = new RoleDto();
        BeanUtils.copyProperties(dbo, roleDto);
        return roleDto;
    }

    @Override
    public RoleEntity convertToDbo(RoleDto dto) {
        final RoleEntity roleEntity = new RoleEntity();
        BeanUtils.copyProperties(dto, roleEntity);
        return roleEntity;
    }
}