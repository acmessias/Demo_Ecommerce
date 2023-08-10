package com.rampup.demo.dtos;

import java.io.Serializable;

import com.rampup.demo.entities.Role;
import com.rampup.demo.entities.enums.Authorities;

public class RoleDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Authorities authority;

    public RoleDTO() {
    }

    public RoleDTO(Role obj) {
        super();
        this.id = obj.getId();
        this.authority = obj.getAuthority();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Authorities getAuthority() {
        return authority;
    }

    public void setAuthority(Authorities authority) {
        this.authority = authority;
    }

}
