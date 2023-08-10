package com.rampup.demo.dtos;

import java.io.Serializable;

import com.rampup.demo.entities.ProductOffering;
import com.rampup.demo.entities.TimePeriod;
import com.rampup.demo.entities.enums.POState;

public class ProductOfferingDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private TimePeriod validFor;
    private POState state;
    private Boolean sellIndicator;

    public ProductOfferingDTO() {
    }

    public ProductOfferingDTO(ProductOffering obj) {
        super();
        this.id = obj.getId();
        this.name = obj.getName();
        this.validFor = obj.getValidFor();
        this.state = obj.getState();
        this.sellIndicator = obj.getSellIndicator();
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

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    public POState getState() {
        return state;
    }

    public void setState(POState state) {
        this.state = state;
    }

    public Boolean getSellIndicator() {
        return sellIndicator;
    }

    public void setSellIndicator(Boolean sellIndicator) {
        this.sellIndicator = sellIndicator;
    }

}
