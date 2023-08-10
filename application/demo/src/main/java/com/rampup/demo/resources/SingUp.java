package com.rampup.demo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rampup.demo.dtos.UserDTO;
import com.rampup.demo.entities.User;
import com.rampup.demo.entities.enums.Authorities;
import com.rampup.demo.services.UserService;
import com.rampup.demo.services.exceptions.ForbiddenException;
import com.rampup.demo.services.exceptions.ResourceNotFoundException;

@RestController
@RequestMapping(value = "/singup")

public class SingUp {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User obj) {
        if (obj.getRole().size() == 1 && obj.getRole().get(0).getAuthority().equals(Authorities.OPERATOR)) {
            obj = service.insert(obj);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id").buildAndExpand(obj.getId()).toUri();
            return ResponseEntity.created(uri).body(obj);
        } else {
            throw new ForbiddenException(null);

        }
    }
}
