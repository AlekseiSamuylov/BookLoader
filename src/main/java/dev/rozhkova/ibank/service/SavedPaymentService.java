package dev.rozhkova.ibank.service;

import dev.rozhkova.ibank.converter.SavedPaymentConverter;
import dev.rozhkova.ibank.dto.PaymentHistoryDto;
import dev.rozhkova.ibank.dto.SavedPaymentDto;
import dev.rozhkova.ibank.entity.SavedPaymentEntity;
import dev.rozhkova.ibank.repository.SavedPaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SavedPaymentService {

    private SavedPaymentRepository savedPaymentRepository;
    private SavedPaymentConverter savedPaymentConverter;

    public void savePayment(final PaymentHistoryDto paymentHistoryDto) {
        SavedPaymentDto savedPaymentDto = new SavedPaymentDto();
        savedPaymentDto.setPaymentOperation(paymentHistoryDto.getPaymentOperation());
        savedPaymentDto.setPaymentAccount(paymentHistoryDto.getPaymentAccount());
        savedPaymentDto.setUser(paymentHistoryDto.getUser());
        savedPaymentDto.setMoneyAmount(paymentHistoryDto.getMoneyAmount());

        SavedPaymentEntity savedPaymentEntity = savedPaymentConverter.convertToDbo(savedPaymentDto);
        savedPaymentRepository.save(savedPaymentEntity);
    }

    public List<SavedPaymentDto> getAllSavedPayment() {
        return savedPaymentRepository.findAll().stream().map(savedPaymentConverter::convertToDto).collect(Collectors.toList());
    }
}
