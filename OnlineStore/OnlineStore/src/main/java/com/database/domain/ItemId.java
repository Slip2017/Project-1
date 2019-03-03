package com.database.domain;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;


/**
 * @author  Shynkarenko Eduard
 *
 */

@Embeddable
public class ItemId implements Serializable {

    @ManyToOne
    private Order order;
    @ManyToOne
    private Product product;

    public Order getOrder() {
        return order;
    }

    public Product getProduct() {
        return product;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemId that = (ItemId) o;

        if (product != null ? !product.equals(that.product) : that.product != null) return false;
        if (order != null ? !order.equals(that.order) : that.order != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (product != null ? product.hashCode() : 0);
        result = 31 * result + (order != null ? order.hashCode() : 0);
        return result;
    }
}
