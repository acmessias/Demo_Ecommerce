package com.rampup.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.rampup.demo.entities.Customer;
import com.rampup.demo.entities.Order;
import com.rampup.demo.entities.User;
import com.rampup.demo.repositories.CustomerRepository;
import com.rampup.demo.services.exceptions.DatabaseException;
import com.rampup.demo.services.exceptions.InvalidDocumentException;
import com.rampup.demo.services.exceptions.NoContent;
import com.rampup.demo.services.exceptions.ResourceNotFoundException;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public List<Customer> findAll() {
        //return repository.findAll();
        List<Customer> customer = new ArrayList<>();
        List<Customer> customerList = repository.findAll();
        for (Customer o : customerList) {
            if (o.getIsActive()) {
                customer.add(o);
            }
        }
        return customer;
    }

    public Customer findById(Long id) {
        Optional<Customer> obj = repository.findById(id);
        return obj.get();
    }

    public Customer insert(Customer obj) {
        if (!isValidDocument(obj.getDocumentNumber())) {
            System.out.println(obj.getCustomerName());
            throw new InvalidDocumentException(obj.getDocumentNumber());}
        return repository.save(obj);
    }

    public void delete(Long id) {
        try {
            Customer customer = this.findById(id);
            customer.setIsActive(false);
            customer = this.update(id, customer);
            throw new NoContent("Item has been deleted");
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }

    }

    public Customer update(Long id, Customer obj) {
        try {
            Customer entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Customer entity, Customer obj) {
        entity.setCustomerName(obj.getCustomerName());
        entity.setCustomerStatus(obj.getCustomerStatus());
        entity.setCustomerType(obj.getCustomerType());
        entity.setCreditScore(obj.getCreditScore());
        entity.setPassword(obj.getPassword());
    }

    public boolean hasAccess(Long id, String email) {
        try {
            User user = findById(id).getUser();
            return (user != null && user.getEmail().equals(email));
        } catch (Exception e) {
            return false;
        }
    }
    
    private boolean isValidDocument(String document) {
        boolean result = false;
        char dig10, dig11;
        int sm, i, r, num, peso;
        
        if (document.length() == 11 && document.matches("[0-9]+")) {
          sm = 0;
          peso = 10;
          for (i=0; i<9; i++) {              
              num = (int)(document.charAt(i) - 48); 
              sm = sm + (num * peso);
              peso = peso - 1;
          }

          r = 11 - (sm % 11);
          if ((r == 10) || (r == 11))
              dig10 = '0';
          else 
              dig10 = (char)(r + 48); 

          sm = 0;
          peso = 11;
          for(i=0; i<10; i++) {
              num = (int)(document.charAt(i) - 48);
              sm = sm + (num * peso);
              peso = peso - 1;
          }

          r = 11 - (sm % 11);
          if ((r == 10) || (r == 11))
              dig11 = '0';
          else 
              dig11 = (char)(r + 48);

          if ((dig10 == document.charAt(9)) && (dig11 == document.charAt(10)))
              result = true;
        }
        
        return result;
    }

}
