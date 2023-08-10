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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tb_time_period")
public class TimePeriod implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant startDateTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant endDateTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant retireDateTime;

    @OneToMany(mappedBy = "validFor", cascade = CascadeType.MERGE)
    private List<ProductOffering> productOfferings;

    @OneToMany(mappedBy = "validFor", cascade = CascadeType.PERSIST)
    private List<Characteristic> characteristics;

    public TimePeriod() {
    }

    public TimePeriod(Long id, Instant startDateTime, Instant endDateTime, Instant retireDateTime) {
        super();
        this.id = id;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.retireDateTime = retireDateTime;
        this.characteristics = new ArrayList<Characteristic>();
        this.productOfferings = new ArrayList<ProductOffering>();
    }

    public void addCharacteristic(Characteristic characteristic) {
        characteristics.add(characteristic);
    }

    public void addProductOffering(ProductOffering productOffering) {
        productOfferings.add(productOffering);
    }

    public Instant getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Instant startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Instant getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Instant endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Instant getRetireDateTime() {
        return retireDateTime;
    }

    public void setRetireDateTime(Instant retireDateTime) {
        this.retireDateTime = retireDateTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(endDateTime, retireDateTime, startDateTime);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TimePeriod other = (TimePeriod) obj;
        return Objects.equals(endDateTime, other.endDateTime) && Objects.equals(retireDateTime, other.retireDateTime)
                && Objects.equals(startDateTime, other.startDateTime);
    }

}
