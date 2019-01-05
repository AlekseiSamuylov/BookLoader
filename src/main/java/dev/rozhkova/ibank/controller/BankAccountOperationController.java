package dev.rozhkova.ibank.controller;

import dev.rozhkova.ibank.converter.BankAccountConverter;
import dev.rozhkova.ibank.converter.UserConverter;
import dev.rozhkova.ibank.dto.BankAccountDto;
import dev.rozhkova.ibank.dto.BankCardDto;
import dev.rozhkova.ibank.dto.UserDto;
import dev.rozhkova.ibank.entity.BankAccountEntity;
import dev.rozhkova.ibank.entity.UserEntity;
import dev.rozhkova.ibank.exception.UserException;
import dev.rozhkova.ibank.service.BankAccountService;
import dev.rozhkova.ibank.service.BankCardService;
import dev.rozhkova.ibank.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class BankAccountOperationController {
    private final UserService userService;
    private final BankAccountService bankAccountService;
    private final BankCardService bankCardService;
    private final UserConverter userConverter;
    private final BankAccountConverter bankAccountConverter;

    @GetMapping("/users/{id}/bankAccount/list")
    public ResponseEntity getAllBankAccountByUser(@PathVariable final Long id) {
        try{
            final UserDto userDto = userService.getUserById(id);
            final List<BankAccountDto> allBankAccountByUser = bankAccountService.getAllBankAccountByUser(userConverter.convertToDbo(userDto));
            if (allBankAccountByUser != null) {
                return new ResponseEntity<>(allBankAccountByUser, HttpStatus.FOUND);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (UserException ex) {
            return new ResponseEntity<>(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{userId}/bankAccount/list/{bankAccountId}")
    public ResponseEntity getAllBankAccountByUserAndId(@PathVariable final Long userId,
                                                       @PathVariable final Long bankAccountId) {
        try{
            final UserDto userDto = userService.getUserById(userId);
            final BankAccountDto bankAccountByIdAndUser = bankAccountService.getBankAccountByUserAndId(userConverter.convertToDbo(userDto), bankAccountId);
            return new ResponseEntity<>(bankAccountByIdAndUser, HttpStatus.FOUND);
        } catch (UserException ex) {
            return new ResponseEntity<>(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{userId}/bankAccount/list/{bankAccountId}/bankCard/list")
    public ResponseEntity getAllBankCardByAccount(@PathVariable final Long userId,
                                                     @PathVariable final Long bankAccountId) {
        try {
            final UserDto userDto = userService.getUserById(userId);
            final BankAccountDto bankAccountDto = bankAccountService.getBankAccountByUserAndId(userConverter.convertToDbo(userDto), bankAccountId);
            final List<BankCardDto> allBankCardByBankAccount = bankCardService.getAllBankCardByBankAccount(bankAccountConverter.convertToDbo(bankAccountDto));
            if (allBankCardByBankAccount != null) {
                return new ResponseEntity<>(allBankCardByBankAccount, HttpStatus.FOUND);
            } else {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } catch (UserException ex) {
            return new ResponseEntity<>(ex.toString(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}