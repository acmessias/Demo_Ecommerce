package com.rampup.demo.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rampup.demo.entities.enums.AddressType;

@Entity
@Table(name = "tb_address", uniqueConstraints = { @UniqueConstraint(columnNames = { "ZIP_CODE", "HOUSE_NUMBER",
        "COUNTRY", "COMPLEMENT", "ADDRESS_TYPE", "CLIENT_ID" }) })
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;

    @Column(name = "HOUSE_NUMBER")
    private Integer houseNumber;

    private String neighborhood;

    @Column(name = "COMPLEMENT")
    private String complement;

    @Column(name = "ZIP_CODE")
    private String zipCode;

    @Column(name = "ADDRESS_TYPE")
    private Integer addressType;

    @Column(name = "COUNTRY")
    private String country;

    @JsonIgnore
    @ManyToOne
    private Customer client;

    public Address() {
    }

    public Address(Long id, String street, Integer houseNumber, String neighborhood, String complement, String zipCode,
            AddressType addressType, String country) {
        super();
        this.id = id;
        this.street = street;
        this.houseNumber = houseNumber;
        this.neighborhood = neighborhood;
        this.complement = complement;
        this.zipCode = zipCode;
        setAddressType(addressType);
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public AddressType getAddressType() {
        return AddressType.valueOf(addressType);
    }

    public void setAddressType(AddressType addressType) {
        if (addressType != null) {
            this.addressType = addressType.getCode();
        }
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Customer getClient() {
        return client;
    }

    public void setClient(Customer client) {
        this.client = client;
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressType, complement, country, houseNumber, neighborhood, street, zipCode);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Address other = (Address) obj;
        return addressType == other.addressType && Objects.equals(complement, other.complement)
                && Objects.equals(country, other.country) && Objects.equals(houseNumber, other.houseNumber)
                && Objects.equals(neighborhood, other.neighborhood) && Objects.equals(street, other.street)
                && Objects.equals(zipCode, other.zipCode);
    }

}
