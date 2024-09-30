package com.excelr.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excelr.model.Payment;
@Repository
public interface PaymentRepo extends JpaRepository<Payment, Long> {

}
