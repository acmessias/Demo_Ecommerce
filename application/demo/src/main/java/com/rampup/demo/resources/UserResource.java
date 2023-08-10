package com.rampup.demo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.rampup.demo.entities.Role;
import com.rampup.demo.entities.User;
import com.rampup.demo.entities.enums.Authorities;
import com.rampup.demo.services.UserService;
import com.rampup.demo.services.exceptions.ForbiddenException;
import com.rampup.demo.services.exceptions.ResourceNotFoundException;

@RestController
@RequestMapping(value = "/users")

public class UserResource {

    @Autowired
    private UserService service;

    /*
     * @GetMapping public ResponseEntity<List<User>> findAll() { List<User> list =
     * service.findAll(); return ResponseEntity.ok().body(list); }
     */

    @PostAuthorize("hasRole('ADMIN') or returnObject.getBody().getEmail() == authentication.principal")
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User obj) {
        if (obj.getEmail().equals("superadmin@gmail.com")) {
            throw new ForbiddenException(null);
        }
        if (obj.getRole().size() == 0) {
            obj.getRole().add(new Role(2L, Authorities.valueOf(2)));
        }
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR') and @userService.hasAccess(#id, authentication.principal)")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR') and @userService.hasAccess(#id, authentication.principal)")
    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAllDTO() {
        List<User> list = service.findAll();
        List<UserDTO> listDTO = list.stream().map(obj -> new UserDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
}
