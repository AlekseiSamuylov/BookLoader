package dev.rozhkova.ibank.controller;

import dev.rozhkova.ibank.dto.BankAccountDto;
import dev.rozhkova.ibank.exception.UserException;
import dev.rozhkova.ibank.service.BankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
/*@RequestMapping("/bank_account")*/
public class BankAccountController {
    private final BankAccountService bankAccountService;

    @GetMapping("/bank_account/list")
    public ResponseEntity getAllBankAccount() {
        try {
            final List<BankAccountDto> allBankAccount = bankAccountService.getAllBankAccount();
            if (allBankAccount != null) {
                return new ResponseEntity<>(allBankAccount, HttpStatus.FOUND);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (UserException ex) {
            return new ResponseEntity<>(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/bank_account/create")
    public ResponseEntity createBankAccount(@RequestBody final BankAccountDto bankAccountDto) {
        try {
            bankAccountService.createBankAccount(bankAccountDto);
            return new ResponseEntity<>("New bank account created", HttpStatus.CREATED);

        } catch (UserException ex) {
            return new ResponseEntity<>(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/bank_account/remove")
    public ResponseEntity removeBankAccount(@RequestBody final BankAccountDto bankAccountDto) {
        try{
            bankAccountService.removeBankAccount(bankAccountDto);
            return new ResponseEntity<>("Bank account removed", OK);
        } catch (UserException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}