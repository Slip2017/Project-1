package com.database.dao.interfaces;

/**
 * @author  Shynkarenko Eduard
 *
 */
public interface ItemDAO {
    public void updateTotalQty(Long orderId, Long productId, Integer qty);
    public  void deleteItem(Long orderId, Long productId);
}
