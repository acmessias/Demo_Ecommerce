package com.rampup.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.rampup.demo.entities.Characteristic;
import com.rampup.demo.repositories.CharacteristicRepository;
import com.rampup.demo.services.exceptions.DatabaseException;
import com.rampup.demo.services.exceptions.NoContent;
import com.rampup.demo.services.exceptions.ResourceNotFoundException;

@Service
public class CharacteristicService {

    @Autowired
    private CharacteristicRepository repository;

    public List<Characteristic> findAll() {
        //return repository.findAll();
        List<Characteristic> characteristic = new ArrayList<>();
        List<Characteristic> characteristicList = repository.findAll();
        for (Characteristic o : characteristicList) {
            if (o.getIsActive()) {
                characteristic.add(o);
            }
        }
        return characteristic;
    }

    public Characteristic findById(Long id) {
        Optional<Characteristic> obj = repository.findById(id);
        return obj.get();
    }

    public Characteristic insert(Characteristic obj) {
        if (obj.getValidFor().getStartDateTime().compareTo( obj.getValidFor().getEndDateTime()) > 0) {
            throw new DatabaseException("Invalid Date time");
        }
        return repository.save(obj);
    }

    public void delete(Long id) {
        try {
            Characteristic characteristic = this.findById(id);
            characteristic.setIsActive(false);
            characteristic = this.update(id, characteristic);
            throw new NoContent("Item has been deleted");
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }

    }

    public Characteristic update(Long id, Characteristic obj) {
        try {
            Characteristic entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Characteristic entity, Characteristic obj) {
        entity.setName(obj.getName());
        entity.setType(obj.getType());
        entity.setValidFor(obj.getValidFor());
        entity.setValueType(obj.getValueType());
    }
}
