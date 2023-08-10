package com.rampup.demo.dtos;

import java.io.Serializable;
import java.time.Instant;
import com.rampup.demo.entities.Address;
import com.rampup.demo.entities.Order;

public class OrderDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Instant instant;
    private Address deliveryAddress;

    public OrderDTO() {
    }

    public OrderDTO(Order obj) {
        super();
        this.id = obj.getId();
        this.instant = obj.getInstant();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

}
