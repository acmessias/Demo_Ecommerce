package com.rampup.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.rampup.demo.entities.Order;
import com.rampup.demo.entities.User;
import com.rampup.demo.repositories.OrderRepository;
import com.rampup.demo.services.exceptions.DatabaseException;
import com.rampup.demo.services.exceptions.NoContent;
import com.rampup.demo.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public List<Order> findAll() {
        // return repository.findAll();
        List<Order> order = new ArrayList<>();
        List<Order> orderList = repository.findAll();
        for (Order o : orderList) {
            if (o.getIsActive()) {
                order.add(o);
            }
        }
        return order;
    }

    public Order findById(Long id) {
        Optional<Order> obj = repository.findById(id);
        return obj.get();
    }

    public Order insert(Order obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        try {
            Order order = this.findById(id);
            order.setIsActive(false);
            order = this.update(id, order);
            throw new NoContent("Item has been deleted");
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }

    }

    public Order update(Long id, Order obj) {
        try {
            Order entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Order entity, Order obj) {
    }

    public boolean hasAccess(Long id, String email) {
        try {
            User user = findById(id).getClient().getUser();
            return (user != null && user.getEmail().equals(email));
        } catch (Exception e) {
            return false;
        }
    }

}
