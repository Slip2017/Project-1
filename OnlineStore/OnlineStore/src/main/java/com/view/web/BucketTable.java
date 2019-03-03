package com.view.web;

import com.database.domain.Item;
import com.database.domain.Order;
import com.database.domain.Product;
import com.database.domain.User;
import com.database.service.OrderService;
import com.database.service.UserService;
import org.primefaces.context.RequestContext;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;


/**
 * @author  Shynkarenko Eduard
 *
 */

@ManagedBean
@SessionScoped
public class BucketTable implements Serializable {

    @ManagedProperty("#{userService}")
    private UserService userService;

    @ManagedProperty("#{orderService}")
    private OrderService orderService;

    @ManagedProperty("#{loginRegister}")
    private LoginRegisterView loginRegister;

    private Set<Item> items = new HashSet<>(0);
    private BigDecimal totalPrice = new BigDecimal(0);
    private String deliveryAddress;
    private Order order = new Order();

    public BucketTable() {
    }

    public void addProduct(Product product) {
        Item item = new Item();
        item.setProduct(product);
        item.setTotalQuantity(1);
        items.add(item);
        System.out.println(items.size());
    }

    public BigDecimal totalPrice() {
        totalPrice = new BigDecimal(0);
        for (Item item : items) {
            totalPrice = totalPrice.add(item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getTotalQuantity())));
        }
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getTotalPrice() {

        return totalPrice;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService() {

        return userService;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void setLoginRegister(LoginRegisterView loginRegister) {
        this.loginRegister = loginRegister;
    }

    public LoginRegisterView getLoginRegister() {

        return loginRegister;
    }

    public boolean containsItem(Product prod) {

        for (Item item : items) {
            if (item.getProduct().equals(prod)) {
                return true;
            }
        }

        return false;
    }

    public void itemsSize() {
        System.out.println(items.size());
    }

    public String addOrder() {
        String email = (String) SessionUtils.getSession().getAttribute("email");

        if (email == null) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dlg').show()");

        } else {

            User user = userService.getUserByMail(email);
            order.setUser(user);
            order.setDelivery_address(deliveryAddress);
            order.setDate_time(new Date());
            order.setTotal_price(totalPrice);

            for (Item item : items) {
                item.setOrder(order);
            }

            order.setItems(items);
            orderService.addOrder(order);
            items.clear();
            deliveryAddress = null;
            return "finish";
        }
        return "";
    }
}
