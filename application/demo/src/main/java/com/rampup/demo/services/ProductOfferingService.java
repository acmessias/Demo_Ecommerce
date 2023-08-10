package com.rampup.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.rampup.demo.entities.ProductOffering;
import com.rampup.demo.repositories.ProductOfferingRepository;
import com.rampup.demo.services.exceptions.DatabaseException;
import com.rampup.demo.services.exceptions.NoContent;
import com.rampup.demo.services.exceptions.ResourceNotFoundException;

@Service
public class ProductOfferingService {

    @Autowired
    private ProductOfferingRepository repository;

    public List<ProductOffering> findAll() {
        //return repository.findAll();
        List<ProductOffering> productOffering = new ArrayList<>();
        List<ProductOffering> productOfferingList = repository.findAll();
        for(ProductOffering o : productOfferingList){
            if(o.getIsActive()){
                productOffering.add(o);
            }
        }
        return productOffering;
    }

    public ProductOffering findById(Long id) {
        Optional<ProductOffering> obj = repository.findById(id);
        return obj.get();
    }

    public ProductOffering insert(ProductOffering obj) {
        if (obj.getValidFor().getStartDateTime().compareTo( obj.getValidFor().getEndDateTime()) > 0) {
            throw new DatabaseException("Invalid Date time");
        }
        return repository.save(obj);
    }

    public void delete(Long id) {
        try {
            ProductOffering productOffering = this.findById(id);
            productOffering.setIsActive(false);
            productOffering = this.update(id, productOffering);
            throw new NoContent("Item has been deleted");
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }

    }

    public ProductOffering update(Long id, ProductOffering obj) {
        try {
            ProductOffering entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(ProductOffering entity, ProductOffering obj) {
        entity.setName(obj.getName());
        entity.setValidFor(obj.getValidFor());
        entity.setState(obj.getState());
        entity.setSellIndicator(obj.getSellIndicator());
    }
}
