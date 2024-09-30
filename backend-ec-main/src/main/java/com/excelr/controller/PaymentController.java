package com.excelr.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excelr.Enum.PaymentMethod;
import com.excelr.Exception.PaymentException;
import com.excelr.model.Payment;
import com.excelr.service.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/order-payments")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/makePayment/{orderId}/{userId}/{paymentMethod}")
    public ResponseEntity<Payment> makePayment(
            @PathVariable Integer orderId, 
            @PathVariable Integer userId, 
            @PathVariable PaymentMethod paymentMethod) 
            throws PaymentException {
        Payment createdPayment = paymentService.makePayment(orderId, userId, paymentMethod);
        return ResponseEntity.ok(createdPayment);
    }
}