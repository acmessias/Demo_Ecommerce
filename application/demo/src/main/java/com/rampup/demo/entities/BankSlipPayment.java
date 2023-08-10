package com.rampup.demo.entities;

import java.time.Instant;
import java.util.Objects;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.rampup.demo.entities.enums.PaymentState;

@Entity
@DiscriminatorValue(value = "BankSlipPayment")
public class BankSlipPayment extends Payment {
    private static final long serialVersionUID = 1L;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant dueDate;

    public BankSlipPayment() {
        super();
    }

    public BankSlipPayment(Integer id, PaymentState paymentState, Instant startDate, Instant dueDate) {
        super(id, paymentState);
        this.startDate = startDate;
        this.dueDate = dueDate;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getDueDate() {
        return dueDate;
    }

    public void setDueDate(Instant dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(dueDate, startDate);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        BankSlipPayment other = (BankSlipPayment) obj;
        return Objects.equals(dueDate, other.dueDate) && Objects.equals(startDate, other.startDate);
    }

}
