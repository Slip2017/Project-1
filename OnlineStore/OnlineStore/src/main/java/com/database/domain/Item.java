package com.database.domain;

import javax.persistence.*;
import java.io.Serializable;


/**
 * @author  Shynkarenko Eduard
 *
 */

@Entity
@Table(name = "item", catalog = "utarasa_shop")
@AssociationOverrides({
        @AssociationOverride(name = "itemId.product",
                joinColumns = @JoinColumn(name = "product_id")),
        @AssociationOverride(name = "itemId.order",
                joinColumns = @JoinColumn(name = "order_id")) })
public class Item implements Serializable {

    @EmbeddedId
    private ItemId itemId  = new ItemId() ;

    @Column(name = "total_quantity",  nullable = false)
    private Integer totalQuantity;

    public Item() {}

    @Transient
    public Order getOrder() {
        return getItemId().getOrder();
    }

    @Transient
    public Product getProduct() {
        return getItemId().getProduct();
    }

    public void setOrder(Order order) {
        this.getItemId().setOrder(order);
    }

    public void setProduct(Product product) {
        this.getItemId().setProduct(product);
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public ItemId getItemId() {
        return itemId;
    }

    public void setItemId(ItemId itemId) {
        this.itemId = itemId;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Item that = (Item) o;

        if (getItemId() != null ? !getItemId().equals(that.getItemId())
                : that.getItemId() != null)
            return false;

        return true;
    }

    public int hashCode() {
        return (getItemId() != null ? getItemId().hashCode() : 0);
    }
}
