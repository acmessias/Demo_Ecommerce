package com.rampup.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rampup.demo.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
