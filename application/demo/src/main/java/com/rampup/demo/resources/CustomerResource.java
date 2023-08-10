package com.rampup.demo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import com.rampup.demo.dtos.CustomerDTO;
import com.rampup.demo.entities.Customer;
import com.rampup.demo.services.CustomerService;

@RestController
@RequestMapping(value = "/customers")

public class CustomerResource {

    @Autowired
    private CustomerService service;

    /*
     * @GetMapping public ResponseEntity<List<Customer>> findAll() { List<Customer>
     * list = service.findAll(); return ResponseEntity.ok().body(list); }
     */

    @PreAuthorize("hasRole('ADMIN') or @customerService.hasAccess(#id, authentication.principal)")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Customer> findById(@PathVariable Long id) {
        Customer obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR') and @customerService.hasAccess(#id, authentication.principal)")
    @PostMapping
    public ResponseEntity<Customer> insert(@RequestBody Customer obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR') and @customerService.hasAccess(#id, authentication.principal)")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR') and @customerService.hasAccess(#id, authentication.principal)")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Customer> update(@PathVariable Long id, @RequestBody Customer obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> findAllDTO() {
        List<Customer> list = service.findAll();
        List<CustomerDTO> listDTO = list.stream().map(obj -> new CustomerDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
}
