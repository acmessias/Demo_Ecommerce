package com.rampup.demo.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant instant;
    
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "client_id")
    private Customer client;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "order_id")
    private List<OrderItem> productOfferings = new ArrayList<>();

    @JsonIgnore
    @OneToOne
    private Address deliveryAddress;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "payment_id")
    private Payment payment;
    
    private Boolean isActive = true;

    public Order() {

    }

    public Order(Long id, Instant instant, Customer client, Address deliveryAddress, Payment payment, Boolean isActive) {
        super();
        this.id = id;
        this.instant = instant;
        this.client = client;
        this.deliveryAddress = deliveryAddress;
        this.payment = payment;
        payment.setOrder(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        productOfferings.add(orderItem);
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

    public Customer getClient() {
        return client;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }


    public List<OrderItem> getItems() {
        return productOfferings;
    }

    public Payment getPayment() {
        return payment;
    }
    
    public Double getTotalPrice() {
        Double result = 0.0;
        for (OrderItem item : productOfferings) {
            result += item.getTotalPrice();
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Order other = (Order) obj;
        return Objects.equals(id, other.id);
    }

}
