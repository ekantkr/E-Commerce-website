package com.excelr.service;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excelr.Enum.OrderStatus;
import com.excelr.Enum.PaymentMethod;
import com.excelr.Enum.PaymentStatus;
import com.excelr.Exception.PaymentException;
import com.excelr.Exception.UserException;
import com.excelr.model.Orders;
import com.excelr.model.Payment;
import com.excelr.model.User;
import com.excelr.repo.OrderRepository;
import com.excelr.repo.PaymentRepo;
import com.excelr.repo.UserRepo;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Payment makePayment(Integer orderId, Integer userId,PaymentMethod paymentMethod) throws PaymentException {

        User existingUser = userRepo.findById(userId)
                .orElseThrow(() -> new UserException("User not found in the database."));

        Orders order = orderRepository.findById(orderId)
        		.orElseThrow(() -> new PaymentException("Order not found in the database."));

        if (order.getUser().getUserId() != userId) {
            throw new PaymentException("Order does not belong to the given user.");
        }

        Payment payment = new Payment(); 
        payment.setPaymentAmount(order.getTotalAmount());
        payment.setPaymentDate(new Date());
        payment.setPaymentMethod(paymentMethod);
        payment.setPaymentStatus(PaymentStatus.SUCCESSFUL);
        payment.setUser(existingUser);
        payment.setOrder(order);

        paymentRepo.save(payment);

//        order.setStatus(OrderStatus.SHIPPED);
//        order.setPayment(payment);
//        orderRepository.save(order);

        existingUser.getPayments().add(payment);
        userRepo.save(existingUser);

        return payment;
    }
}