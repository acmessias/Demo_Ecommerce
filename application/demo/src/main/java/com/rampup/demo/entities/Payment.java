package com.rampup.demo.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.rampup.demo.entities.enums.PaymentState;

@Entity
@Table(name = "tb_payment")
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Payment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer paymentState;

    @OneToOne
    private Order order;

    public Payment() {
        super();
    }

    public Payment(Integer id, PaymentState paymentState) {
        super();
        this.id = id;
        setPaymentState(paymentState);
        // this.order = order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PaymentState getPaymentState() {
        return PaymentState.valueOf(paymentState);
    }

    public void setPaymentState(PaymentState paymentState) {
        if (paymentState != null) {
            this.paymentState = paymentState.getCode();
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, paymentState);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Payment other = (Payment) obj;
        return Objects.equals(id, other.id) && paymentState == other.paymentState;
    }

}
