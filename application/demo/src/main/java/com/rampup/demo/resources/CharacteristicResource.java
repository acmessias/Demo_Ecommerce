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

import com.rampup.demo.dtos.CharacteristicDTO;
import com.rampup.demo.dtos.UserDTO;
import com.rampup.demo.entities.Characteristic;
import com.rampup.demo.entities.User;
import com.rampup.demo.services.CharacteristicService;

@RestController
@RequestMapping(value = "/characteristics")

public class CharacteristicResource {

    @Autowired
    private CharacteristicService service;

    /*
     * @GetMapping public ResponseEntity<List<Characteristic>> findAll() {
     * List<Characteristic> list = service.findAll(); return
     * ResponseEntity.ok().body(list); }
     */

    @GetMapping(value = "/{id}")
    public ResponseEntity<Characteristic> findById(@PathVariable Long id) {
        Characteristic obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Characteristic> insert(@RequestBody Characteristic obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Characteristic> update(@PathVariable Long id, @RequestBody Characteristic obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<CharacteristicDTO>> findAllDTO() {
        List<Characteristic> list = service.findAll();
        List<CharacteristicDTO> listDTO = list.stream().map(obj -> new CharacteristicDTO(obj))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
}
