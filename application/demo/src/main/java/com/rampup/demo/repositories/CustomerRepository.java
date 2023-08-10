package com.rampup.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rampup.demo.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
