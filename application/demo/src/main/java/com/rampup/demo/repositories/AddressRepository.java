package com.rampup.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rampup.demo.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
