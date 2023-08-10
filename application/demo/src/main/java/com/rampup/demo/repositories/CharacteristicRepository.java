package com.rampup.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rampup.demo.entities.Characteristic;

public interface CharacteristicRepository extends JpaRepository<Characteristic, Long> {

}
