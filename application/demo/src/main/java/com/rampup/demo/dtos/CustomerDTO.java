package com.rampup.demo.dtos;

import java.io.Serializable;

import com.rampup.demo.entities.Customer;
import com.rampup.demo.entities.enums.CustomerType;

public class CustomerDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String customerName;
    private String documentNumber;
    private String customerStatus;
    private CustomerType customerType;
    private String creditScore;
    private String password;

    public CustomerDTO() {
    }

    public CustomerDTO(Customer obj) {
        super();
        this.id = obj.getId();
        this.customerName = obj.getCustomerName();
        this.documentNumber = obj.getDocumentNumber();
        this.customerStatus = obj.getCustomerStatus();
        this.customerType = obj.getCustomerType();
        this.creditScore = obj.getCreditScore();
        this.password = obj.getPassword();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public String getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(String creditScore) {
        this.creditScore = creditScore;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
