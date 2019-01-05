package dev.rozhkova.ibank.service;

import dev.rozhkova.ibank.converter.UserConverter;
import dev.rozhkova.ibank.entity.UserEntity;
import dev.rozhkova.ibank.dto.UserDto;
import dev.rozhkova.ibank.exception.UserException;
import dev.rozhkova.ibank.repository.UserRepository;
import lombok.AllArgsConstructor;
/*import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;*/
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    //private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserConverter userConverter;

    public UserEntity findByLogin(final String login) {
        return userRepository.findByLogin(login);
    }

    public void create(final UserDto userDto) throws UserException {
        UserEntity userEntity = userConverter.convertToDbo(userDto);
        /*userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));*/
        userRepository.save(userEntity);
    }

    public List<UserDto> getAllUsers() throws UserException {
       return userRepository.findAll().stream().map(userConverter::convertToDto).collect(Collectors.toList());
    }

    public UserDto getUserById(final Long id) throws UserException {
        return userConverter.convertToDto(userRepository.findById(id).get());
    }

    public void removeUser(final UserDto userDto) throws UserException {
        userRepository.delete(userConverter.convertToDbo(userDto));
    }

    public void updateUser(final Long id, final UserDto userAfterUpdate) throws UserException {
        UserEntity userBeforeUpdate = userRepository.findById(id).get();
        UserEntity userAfterUpdateDbo = userConverter.convertToDbo(userAfterUpdate);
        if (userAfterUpdateDbo.getFirstName() != null) {
            userBeforeUpdate.setFirstName(userAfterUpdateDbo.getFirstName());
        } else if (userAfterUpdateDbo.getLastName() != null) {
            userBeforeUpdate.setFirstName(userAfterUpdateDbo.getLastName());
        } else if (userAfterUpdateDbo.getPatronymic() != null) {
            userBeforeUpdate.setPatronymic(userAfterUpdateDbo.getPatronymic());
        } else if (userAfterUpdateDbo.getPassportNumber() != null) {
            userBeforeUpdate.setPassportNumber(userAfterUpdateDbo.getPassportNumber());
        } else if (userAfterUpdateDbo.getEmail() != null) {
            userBeforeUpdate.setEmail(userAfterUpdateDbo.getEmail());
        } else if (userAfterUpdateDbo.getLogin() != null) {
            userBeforeUpdate.setLogin(userAfterUpdateDbo.getLogin());
        } else if (userAfterUpdateDbo.getPassword() != null) {
            userBeforeUpdate.setPassword(userAfterUpdateDbo.getPassword());
        }
        userRepository.save(userBeforeUpdate);
    }
}