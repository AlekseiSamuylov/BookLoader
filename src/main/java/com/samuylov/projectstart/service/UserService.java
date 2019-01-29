package com.samuylov.projectstart.service;

import com.samuylov.projectstart.converter.DtoEntityConverter;
import com.samuylov.projectstart.converter.UserConverter;
import com.samuylov.projectstart.dto.UserDto;
import com.samuylov.projectstart.entity.UserEntity;
import com.samuylov.projectstart.repository.NamedEntityRepository;
import com.samuylov.projectstart.repository.UserRepository;
import com.vaadin.data.provider.Query;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService extends AbstractService<UserDto, UserEntity> {

    public UserService(final NamedEntityRepository<UserEntity> repository,
                       final DtoEntityConverter<UserDto, UserEntity> converter) {
        super(repository, converter);
    }

    public void create(final UserDto user) {
        repository.save(converter.convertToEntity(user));
    }

    public void update(final Long userId, final UserDto user) {
        final UserEntity oldUser = ((UserRepository) repository).findFirstById(userId);

        oldUser.setName(user.getName());
        oldUser.setPassword(user.getPassword());
        oldUser.setRole(user.getRole());
        repository.save(oldUser);
    }

    @Override
    public void save(final UserDto userDto) {
        if (userDto.getId() == null) {
            create(userDto);
        } else {
            update(userDto.getId(), userDto);
        }
    }

    public void delete(final Long userId) {
        repository.deleteById(userId);
    }

    public UserDto getUserById(final Long userId) {
        return converter.convertToDto(((UserRepository) repository).findFirstById(userId));
    }

    public Optional<UserEntity> getUserByName(final String name) {
        return ((UserRepository) repository).findByName(name);
    }

    @Override
    public List<UserDto> getList() {
        return repository.findAll().stream().map(converter::convertToDto).collect(Collectors.toList());
    }
}
