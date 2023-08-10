package com.rampup.demo.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.rampup.demo.entities.Role;
import com.rampup.demo.entities.User;
import com.rampup.demo.repositories.RoleRepository;
import com.rampup.demo.services.exceptions.DatabaseException;
import com.rampup.demo.services.exceptions.ResourceNotFoundException;

@Service
public class RoleService {

    @Autowired
    private RoleRepository repository;

    public List<Role> findAll() {
        return repository.findAll();
    }

    public Role findById(Long id) {
        Optional<Role> obj = repository.findById(id);
        return obj.get();
    }

    public Role insert(Role obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }

    }

    public Role update(Long id, Role obj) {
        try {
            Role entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Role entity, Role obj) {
        entity.setAuthority(obj.getAuthority());
    }
}
