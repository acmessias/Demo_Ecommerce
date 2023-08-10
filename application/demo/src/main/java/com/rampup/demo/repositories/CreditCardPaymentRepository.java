package com.rampup.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rampup.demo.entities.CreditCardPayment;

public interface CreditCardPaymentRepository extends JpaRepository<CreditCardPayment, Long> {

}
