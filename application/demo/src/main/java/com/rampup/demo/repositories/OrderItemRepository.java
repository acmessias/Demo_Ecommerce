package com.rampup.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rampup.demo.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
