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

import com.rampup.demo.dtos.ProductOfferingDTO;
import com.rampup.demo.entities.ProductOffering;
import com.rampup.demo.services.ProductOfferingService;

@RestController
@RequestMapping(value = "/productofferings")

public class ProductOfferingResource {

    @Autowired
    private ProductOfferingService service;

    /*
     * @GetMapping public ResponseEntity<List<ProductOffering>> findAll() {
     * List<ProductOffering> list = service.findAll(); return
     * ResponseEntity.ok().body(list); }
     */

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductOffering> findById(@PathVariable Long id) {
        ProductOffering obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ProductOffering> insert(@RequestBody ProductOffering obj) {
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
    public ResponseEntity<ProductOffering> update(@PathVariable Long id, @RequestBody ProductOffering obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<ProductOfferingDTO>> findAllDTO() {
        List<ProductOffering> list = service.findAll();
        List<ProductOfferingDTO> listDTO = list.stream().map(obj -> new ProductOfferingDTO(obj))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

}
