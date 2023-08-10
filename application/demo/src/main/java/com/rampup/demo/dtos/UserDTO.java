package com.rampup.demo.dtos;

import java.io.Serializable;

import com.rampup.demo.entities.User;

public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String email;
    private String password;

    public UserDTO() {
    }

    public UserDTO(User obj) {
        super();
        this.id = obj.getId();
        this.email = obj.getEmail();
        this.password = obj.getPassword();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
