package com.rampup.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rampup.demo.entities.ProductOffering;

public interface ProductOfferingRepository extends JpaRepository<ProductOffering, Long> {

}
