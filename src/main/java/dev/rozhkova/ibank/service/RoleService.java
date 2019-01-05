package dev.rozhkova.ibank.service;

import dev.rozhkova.ibank.converter.RoleConverter;
import dev.rozhkova.ibank.dto.RoleDto;
import dev.rozhkova.ibank.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    private final RoleConverter roleConverter;

    @Autowired
    public RoleService(final RoleRepository roleRepository, final RoleConverter roleConverter) {
        this.roleRepository = roleRepository;
        this.roleConverter = roleConverter;
    }

    public List<RoleDto> getAllRoles() {
        return roleRepository.findAll().stream().map(roleConverter::convertToDto).collect(Collectors.toList());
    }
}