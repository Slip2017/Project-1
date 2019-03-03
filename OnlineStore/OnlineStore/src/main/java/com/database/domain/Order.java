package com.database.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;


/**
 * @author  Shynkarenko Eduard
 *
 */


@Entity
@Table(name = "orders", catalog = "utarasa_shop")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "order_id", unique = true, nullable = false)
    private Long orderId;

    @Column(name = "is_archive",  nullable = false)
    private Integer isArchive = 0;

    @Column(name = "order_status",  nullable = false)
    private Integer orderStatus = 0;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_time", nullable = false)
    private Date dateTime;

    @Column(name = "total_price", nullable = false, precision = 10)
    private BigDecimal totalPrice;

    @Column(name = "delivery_address", nullable = false, length = 30)
    private String deliveryAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "itemId.order", cascade = CascadeType.ALL)
    private Set<Item> items = new HashSet<>(0);

    public Order() {
    }

    public Order( Date dateTime, BigDecimal totalPrice, String deliveryAddress, User user) {
        this.dateTime = dateTime;
        this.totalPrice = totalPrice;
        this.deliveryAddress = deliveryAddress;
        this.user = user;
    }

    public Long getOrder_id() {
        return orderId;
    }

    public void setOrder_id(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getIs_archive() {
        return isArchive;
    }

    public void setIs_archive(Integer isArchive) {
        this.isArchive = isArchive;
    }

    public Date getDate_time() {
        return dateTime;
    }

    public void setDate_time(Date dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getTotal_price() {
        return totalPrice;
    }

    public void setTotal_price(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDelivery_address() {
        return deliveryAddress;
    }

    public void setDelivery_address(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Integer getOrder_status() {
        return orderStatus;
    }

    public void setOrder_status(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}
