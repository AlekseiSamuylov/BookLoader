package dev.rozhkova.ibank.service;

import dev.rozhkova.ibank.converter.BankConverter;
import dev.rozhkova.ibank.dto.BankDto;
import dev.rozhkova.ibank.exception.UserException;
import dev.rozhkova.ibank.repository.BankRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BankService {

    private BankRepository bankRepository;
    private BankConverter bankConverter;

    public BankDto getBankInfo() throws UserException {
        return bankConverter.convertToDto(bankRepository.findById(1L).get());
    }
}