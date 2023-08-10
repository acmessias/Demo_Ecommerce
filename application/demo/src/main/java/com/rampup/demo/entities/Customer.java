package com.rampup.demo.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rampup.demo.entities.enums.CustomerType;

@Entity
@Table(name = "tb_customer", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "CUSTOMER_NAME", "DOCUMENT_NUMBER" }) })
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CUSTOMER_NAME")
    private String customerName;

    @Column(name = "DOCUMENT_NUMBER", unique = true)
    private String documentNumber;

    private String customerStatus;
    private Integer customerType;
    private String creditScore;
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private List<Order> orders;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private List<Address> addresses;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private Boolean isActive = true;

    public Customer() {
    }

    public Customer(Long id, String customerName, String documentNumber, String customerStatus,
            CustomerType customerType, String creditScore, String password, User user, List<Address> addresses,
            Boolean isActive) {
        super();
        this.id = id;
        this.customerName = customerName;
        this.documentNumber = documentNumber;
        this.customerStatus = customerStatus;
        setCustomerType(customerType);
        this.creditScore = creditScore;
        this.password = password;
        this.user = user;
        this.addresses = addresses;
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
        return CustomerType.valueOf(customerType);
    }

    public void setCustomerType(CustomerType customerType) {
        if (customerType != null) {
            this.customerType = customerType.getCode();
        }
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

    public List<Order> getOrders() {
        return orders;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Customer other = (Customer) obj;
        return Objects.equals(documentNumber, other.documentNumber);
    }

}
