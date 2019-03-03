package com.database.dao.interfaces;

import com.database.domain.Order;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author  Shynkarenko Eduard
 *
 */

public interface OrderDAO {
    public Long addOrder(Date dateTime, BigDecimal totalPrice, String deliveryAddress, Long userId,
                         HashMap<Long, Integer> map);
    public Long addOrder(Order order);
    public Order getOrder(Long orderId);
    public List<Order> getAllOrders();
    public List<Order> getAllArchiveOrders();
    public List<Order> getAllActiveOrders();
    public List<Order> getArchiveOrdersByUser(Long userId);
    public void updateOrderDateTime(Long orderId, String dateTime);
    public void updateOrderIsArchive(Long orderId, Integer flag);
    public void updateOrderStatus(Long orderId, Integer orderStatus);
    public void updateOrderTotalPrice(Long orderId, BigDecimal totalPrice);
    public void updateOrderAddr(Long orderId, String deliveryAddress);
    public void updateTotalQty(Long orderId, Long productId, Integer qty);
    public void deleteItem(Long orderId, Long productId);
    public void deleteOrder(Long orderId);
    public void deleteAllOrders();
}
