package com.excelr.service;

import com.excelr.Enum.PaymentMethod;
import com.excelr.Exception.PaymentException;
import com.excelr.model.Payment;

public interface PaymentService {
    Payment makePayment(Integer orderId, Integer userId, PaymentMethod paymentMethod) throws PaymentException;
}
