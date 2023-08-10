package com.rampup.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rampup.demo.entities.Order;
import com.rampup.demo.entities.User;
import com.rampup.demo.repositories.UserRepository;
import com.rampup.demo.services.exceptions.DatabaseException;
import com.rampup.demo.services.exceptions.ForbiddenException;
import com.rampup.demo.services.exceptions.NoContent;
import com.rampup.demo.services.exceptions.ResourceNotFoundException;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.rampup.demo.entities.User user = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Login not found."));
        if (user.getRole().size() == 1)
            return org.springframework.security.core.userdetails.User.builder().username(user.getEmail())
                    .password(user.getPassword()).roles(user.getRole().get(0).getAuthority().name()).build();
        else if (user.getRole().size() == 2)
            return org.springframework.security.core.userdetails.User.builder().username(user.getEmail())
                    .password(user.getPassword())
                    .roles(user.getRole().get(0).getAuthority().name(), user.getRole().get(1).getAuthority().name())
                    .build();
        else
            return org.springframework.security.core.userdetails.User.builder().username(user.getEmail())
                    .password(user.getPassword())
                    .roles("OPERATOR")
                    .build();
    }

    public List<User> findAll() {
        //return repository.findAll();
        List<User> user = new ArrayList<>();
        List<User> userList = repository.findAll();
        for(User o : userList){
            if(o.getIsActive()){
                user.add(o);
            }
        }
        return user;
    }

    public User findById(Long id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User insert(User obj) {
        try {
            obj.setPassword(passwordEncoder().encode(obj.getPassword()));
            return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("User already registered");
        }
    }

    public void delete(Long id) {
        try {
            User user = this.findById(id);
            user.setIsActive(false);
            user = this.update(id, user);
            throw new NoContent("User has been deleted");
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }

    }

    public User update(Long id, User obj) {
        try {
            User entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(User entity, User obj) {
        entity.setPassword(passwordEncoder().encode(obj.getPassword()));
        entity.setPassword(obj.getPassword());
    }

    public boolean hasAccess(Long id, String email) {
        User user = findById(id);
        return (user != null && user.getEmail().equals(email));
    }

}
