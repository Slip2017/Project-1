package com.database.dao.implementation;

import com.database.dao.interfaces.ItemDAO;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author  Shynkarenko Eduard
 *
 */
public class ItemDAOImpl implements ItemDAO{

    @Override
    public void updateTotalQty(Long orderId, Long productId, Integer qty) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" update Item set totalQuantity = :qty where itemId.order = :orderId and itemId.product =:productId ");
            query.setParameter("qty", qty);
            query.setParameter("orderId", orderId);
            query.setParameter("productId", productId);

            int result = query.executeUpdate();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    @Override
    public void deleteItem(Long orderId, Long productId) {

    }
}

class TestItem{
    public static void main(String[] args) {
        ItemDAOImpl it = new ItemDAOImpl();
        it.updateTotalQty(1L, 10L, 5);
    }
}
