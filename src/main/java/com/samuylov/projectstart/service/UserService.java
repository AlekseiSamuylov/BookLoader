package com.samuylov.projectstart.service;

import com.samuylov.projectstart.converter.UserConverter;
import com.samuylov.projectstart.dto.UserDto;
import com.samuylov.projectstart.entity.UserEntity;
import com.samuylov.projectstart.repository.UserRepository;
import com.vaadin.data.provider.Query;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class UserService extends AbstractService<UserDto> {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public void create(final UserDto user) {
        userRepository.save(userConverter.convertToEntity(user));
    }

    public void update(final Long userId, final UserDto user) {
        final UserEntity oldUser = userRepository.findFirstById(userId);

        oldUser.setName(user.getName());
        oldUser.setPassword(user.getPassword());
        oldUser.setRole(user.getRole());
        userRepository.save(oldUser);
    }

    public void delete(final Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<UserDto> getList() {
        return userRepository.findAll().stream().map(userConverter::convertToDto).collect(Collectors.toList());
    }

    @Override
    public Stream<UserDto> findWithPagination(Query<UserDto, String> query) {
        final PageRequest pageRequest = preparePageRequest(query);
        final List<UserDto> items = userRepository.findAll(pageRequest)
                .stream().map(userConverter::convertToDto).collect(Collectors.toList());
        return items.stream();
    }

    public UserDto getUserById(final Long userId) {
        return userConverter.convertToDto(userRepository.findFirstById(userId));
    }
}
