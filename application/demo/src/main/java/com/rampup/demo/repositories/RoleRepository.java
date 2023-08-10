package com.rampup.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rampup.demo.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
