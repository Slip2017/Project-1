/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view.admin;

import com.database.domain.Item;
import com.database.domain.Order;
import com.database.service.OrderService;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import org.primefaces.context.RequestContext;

/**
 * Created by Администратор.
 */
@ManagedBean
public class OrdersDetailsBean {

    @ManagedProperty("#{show}")
    private ShowOrdersBean showservice;

    public ShowOrdersBean getShowservice() {
        return showservice;
    }

    public void setShowservice(ShowOrdersBean showservice) {
        this.showservice = showservice;
    }

    public void open(Order order) {

        showservice.setSelectedOrder(order);

        Map<String, Object> options = new HashMap<>();
        options.put("resizable", true);
        options.put("draggable", false);
        options.put("modal", true);
        options.put("contentWidth", 680);
        options.put("closable", false);
        RequestContext.getCurrentInstance().openDialog("/pages/orderdetailsview", options, null);
    }

    public void close() {
        RequestContext.getCurrentInstance().closeDialog(null);
    }
}
