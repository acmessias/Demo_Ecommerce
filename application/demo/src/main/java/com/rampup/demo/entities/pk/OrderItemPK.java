package com.rampup.demo.entities.pk;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.rampup.demo.entities.Order;
import com.rampup.demo.entities.ProductOffering;

@Embeddable
public class OrderItemPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "productoffering_id")
    private ProductOffering productoffering;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ProductOffering getProductOffering() {
        return productoffering;
    }

    public void setProductOffering(ProductOffering productoffering) {
        this.productoffering = productoffering;
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, productoffering);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OrderItemPK other = (OrderItemPK) obj;
        return Objects.equals(order, other.order) && Objects.equals(productoffering, other.productoffering);
    }

}
