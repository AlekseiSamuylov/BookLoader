package dev.rozhkova.ibank.service;

import dev.rozhkova.ibank.converter.PaymentHistoryConverter;
import dev.rozhkova.ibank.dto.PaymentHistoryDto;
import dev.rozhkova.ibank.entity.PaymentHistoryEntity;
import dev.rozhkova.ibank.exception.UserException;
import dev.rozhkova.ibank.repository.PaymentHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentHistoryService {

    private PaymentHistoryRepository paymentHistoryRepository;
    private PaymentHistoryConverter paymentHistoryConverter;

    public void create(final PaymentHistoryDto paymentHistoryDto) throws UserException{
        PaymentHistoryEntity paymentHistoryEntity = paymentHistoryConverter.convertToDbo(paymentHistoryDto);
        paymentHistoryRepository.save(paymentHistoryEntity);
    }

    public List<PaymentHistoryDto> getAllPaymentHistory() {
        return paymentHistoryRepository.findAll().stream().map(paymentHistoryConverter::convertToDto).collect(Collectors.toList());
    }

    public PaymentHistoryDto getPaymentHistoryById(final Long id) {
        return paymentHistoryConverter.convertToDto(paymentHistoryRepository.findById(id).get());
    }
}