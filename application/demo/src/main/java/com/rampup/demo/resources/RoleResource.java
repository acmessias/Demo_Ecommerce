package com.rampup.demo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rampup.demo.dtos.RoleDTO;
import com.rampup.demo.entities.Role;
import com.rampup.demo.services.RoleService;

@RestController
@RequestMapping(value = "/roles")

public class RoleResource {

    @Autowired
    private RoleService service;

    /*
     * @GetMapping public ResponseEntity<List<Role>> findAll() { List<Role> list =
     * service.findAll(); return ResponseEntity.ok().body(list); }
     */

    @GetMapping(value = "/{id}")
    public ResponseEntity<Role> findById(@PathVariable Long id) {
        Role obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Role> insert(@RequestBody Role obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Role> update(@PathVariable Long id, @RequestBody Role obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<RoleDTO>> findAllDTO() {
        List<Role> list = service.findAll();
        List<RoleDTO> listDTO = list.stream().map(obj -> new RoleDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

}
