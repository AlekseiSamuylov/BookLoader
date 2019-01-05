package dev.rozhkova.ibank.controller;

import dev.rozhkova.ibank.dto.PaymentHistoryDto;
import dev.rozhkova.ibank.dto.SavedPaymentDto;
import dev.rozhkova.ibank.dto.UserDto;
import dev.rozhkova.ibank.exception.UserException;
import dev.rozhkova.ibank.service.PaymentHistoryService;
import dev.rozhkova.ibank.service.SavedPaymentService;
import dev.rozhkova.ibank.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class SavedPaymentController {
    private final SavedPaymentService savedPaymentService;
    private final UserService userService;
    private final PaymentHistoryService paymentHistoryService;

    @GetMapping("/{userId}/saved/list")
    public List<SavedPaymentDto> getAllSavedPayments(@PathVariable final Long userId) {
        List<SavedPaymentDto> list = new ArrayList<>();
        final UserDto userDto;

        try {
             userDto = userService.getUserById(userId);


        } catch (UserException ex) {
            return list; // костыль
        }

        for (SavedPaymentDto saved : savedPaymentService.getAllSavedPayment()) {

            if (saved.getUser().equals(userDto)) {
                list.add(saved);
            }
        }

        return list;
    }

    @PostMapping("/{userId}/saved/save")
    public ResponseEntity savePayment(@PathVariable final Long userId,
                              @RequestBody final Long paymentHistoryId) {
        PaymentHistoryDto paymentHistoryDto = paymentHistoryService.getPaymentHistoryById(paymentHistoryId);
        try {
            final UserDto userDto = userService.getUserById(userId);

            if (paymentHistoryDto.getUser().equals(userDto)) {
                return new ResponseEntity<>("Payment saved", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Payment not found", HttpStatus.NOT_FOUND);
            }
        } catch (UserException ex) {
            return new ResponseEntity<>(ex.toString(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
