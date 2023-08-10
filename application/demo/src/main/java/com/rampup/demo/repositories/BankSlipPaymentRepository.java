package com.rampup.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rampup.demo.entities.BankSlipPayment;

public interface BankSlipPaymentRepository extends JpaRepository<BankSlipPayment, Long> {

}
