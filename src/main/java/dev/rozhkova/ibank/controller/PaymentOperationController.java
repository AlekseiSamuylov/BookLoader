package dev.rozhkova.ibank.controller;

import dev.rozhkova.ibank.converter.BankAccountConverter;
import dev.rozhkova.ibank.converter.UserConverter;
import dev.rozhkova.ibank.dto.BankAccountDto;
import dev.rozhkova.ibank.dto.PaymentHistoryDto;
import dev.rozhkova.ibank.dto.SavedPaymentDto;
import dev.rozhkova.ibank.dto.UserDto;
import dev.rozhkova.ibank.exception.UserException;
import dev.rozhkova.ibank.service.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class PaymentOperationController {

    private final UserService userService;
    private final BankAccountService bankAccountService;
    private final BankCardService bankCardService;
    private final UserConverter userConverter;
    private final BankAccountConverter bankAccountConverter;
    private final PaymentHistoryService paymentHistoryService;
    private final SavedPaymentService savedPaymentService;

    @PostMapping("/{userId}/bankAccount/list/{bankAccountId}/payments")
    public ResponseEntity makePayment(@PathVariable final Long userId,
                                      @PathVariable final Long bankAccountId,
                                      @RequestBody final PaymentHistoryDto paymentHistoryDto) {
        try {
            final UserDto userDto = userService.getUserById(userId);
            final BankAccountDto bankAccountByIdAndUser = bankAccountService.getBankAccountByUserAndId(userConverter.convertToDbo(userDto), bankAccountId);
            Double moneyBeforeOperation = bankAccountByIdAndUser.getMoneyAmount();
            Double moneyAfterOperation = moneyBeforeOperation - paymentHistoryDto.getMoneyAmount();
            bankAccountByIdAndUser.setMoneyAmount(moneyAfterOperation);
            bankAccountService.createBankAccount(bankAccountByIdAndUser);

            paymentHistoryDto.setUser(userDto);
            System.out.println(paymentHistoryDto.getMoneyAmount());
            paymentHistoryService.create(paymentHistoryDto);
            return new ResponseEntity<>("Payment record created", HttpStatus.CREATED);
        } catch (UserException ex) {
            return new ResponseEntity<>(ex.toString(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}