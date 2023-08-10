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

import com.rampup.demo.dtos.OrderDTO;
import com.rampup.demo.entities.Order;
import com.rampup.demo.entities.OrderItem;
import com.rampup.demo.entities.Payment;
import com.rampup.demo.services.OrderService;

@RestController
@RequestMapping(value = "/orders")

public class OrderResource {
    public class Test {
        private Payment payment;
        private List<OrderItem> items;
    }

    @Autowired
    private OrderService service;

    /*
     * @GetMapping public ResponseEntity<List<Order>> findAll() { List<Order> list =
     * service.findAll(); return ResponseEntity.ok().body(list); }
     */

    @PreAuthorize("hasRole('ADMIN') or @orderService.hasAccess(#id, authentication.principal)")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id) {
        Order obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    //@PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    @PostMapping
    public ResponseEntity<Order> insert(@RequestBody Order obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR') and @orderService.hasAccess(#id, authentication.principal)")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR') and @orderService.hasAccess(#id, authentication.principal)")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Order> update(@PathVariable Long id, @RequestBody Order obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<OrderDTO>> findAllDTO() {
        List<Order> list = service.findAll();
        List<OrderDTO> listDTO = list.stream().map(obj -> new OrderDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
}
