package com.rampup.demo.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rampup.demo.entities.enums.POState;

@Entity
@Table(name = "tb_product_offering")
public class ProductOffering implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "valid_for_id")
    private TimePeriod validFor;

    private Integer state;
    private Boolean sellIndicator;
    private Double price;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tb_productoffering_characteristic", joinColumns = @JoinColumn(name = "productofferings_id"), inverseJoinColumns = @JoinColumn(name = "characteristic_id"))
    private Set<Characteristic> characteristics = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "id.productoffering")
    private Set<OrderItem> productOfferings = new HashSet<>();

    private Boolean isActive = true;

    public ProductOffering() {

    }

    public ProductOffering(Long id, String name, TimePeriod validFor, POState state, Boolean sellIndicator,
            Boolean isActive, Double price) {
        super();
        this.id = id;
        this.name = name;
        this.validFor = validFor;
        validFor.addProductOffering(this);
        setState(state);
        this.sellIndicator = sellIndicator;
        this.price = price;
    }

    public void addCharacteristic(Characteristic characteristic) {
        characteristics.add(characteristic);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TimePeriod getValidFor() {
        return validFor;
    }
    
    public Double getPrice() {
        return price;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    public POState getState() {
        return POState.valueOf(state);
    }

    public void setState(POState state) {
        if (state != null) {
            this.state = state.getCode();
        }
    }

    public Boolean getSellIndicator() {
        return sellIndicator;
    }

    public void setSellIndicator(Boolean sellIndicator) {
        this.sellIndicator = sellIndicator;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<Characteristic> getCharacteristics() {
        return characteristics;
    }

    @JsonIgnore
    public Set<Order> getOrders() {
        Set<Order> set = new HashSet<>();
        for (OrderItem x : productOfferings) {
            set.add(x.getOrder());
        }
        return set;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sellIndicator, state, validFor);
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
        ProductOffering other = (ProductOffering) obj;
        return Objects.equals(name, other.name) && Objects.equals(sellIndicator, other.sellIndicator)
                && state == other.state && Objects.equals(validFor, other.validFor);
    }

}
