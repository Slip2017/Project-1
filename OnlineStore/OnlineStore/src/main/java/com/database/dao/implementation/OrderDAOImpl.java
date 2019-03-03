package com.database.dao.implementation;

import com.database.dao.interfaces.OrderDAO;
import com.database.domain.*;
import org.hibernate.*;

import java.math.BigDecimal;
import java.util.*;


/**
 * @author  Shynkarenko Eduard
 *
 */


public class OrderDAOImpl implements OrderDAO {

    @Override
    public Long addOrder(Date dateTime, BigDecimal totalPrice, String deliveryAddress, Long userId,
                         HashMap<Long, Integer> map) {

        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        Long orderId = null;

        try {
            tx = session.beginTransaction();

            User user = (User) session.load(User.class, userId);
            Order order = new Order(dateTime, totalPrice, deliveryAddress, user);

            for (Map.Entry<Long, Integer> entry : map.entrySet()) {
                Item item = new Item();
                Product product = new Product();
                product.setProduct_id(entry.getKey());

                item.setProduct(product);
                item.setOrder(order);
                item.setTotalQuantity(entry.getValue());

                order.getItems().add(item);
            }

            orderId = (Long) session.save(order);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }
        return orderId;
    }

    @Override
    public Long addOrder(Order order) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        Long orderId = null;

        try {
            tx = session.beginTransaction();
            orderId = (Long) session.save(order);

            tx.commit();
        } catch (HibernateException e) {

            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }
        return orderId;

    }

    @Override
    public Order getOrder(Long orderId) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        Order order = null;
        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" from Order  where orderId =:orderId");
            query.setParameter("orderId", orderId);

            List list = query.list();
            order = (Order) list.get(0);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        List<Order> list = null;
        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" from Order");
            list = query.list();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return list;
    }

    @Override
    public List<Order> getAllArchiveOrders() {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        List<Order> list = null;
        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" from Order where isArchive=:flag");
            query.setParameter("flag", 1);
            list = query.list();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return list;
    }

    @Override
    public List<Order> getAllActiveOrders() {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        List<Order> list = null;
        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" from Order where isArchive=:flag");
            query.setParameter("flag", 0);
            list = query.list();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return list;
    }

    @Override
    public List<Order> getArchiveOrdersByUser(Long user_id) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        List<Order> list = null;
        try {
            tx = session.beginTransaction();
            User user = (User) session.load(User.class, user_id);
            Query query = session.createQuery(" from Order where user = :user and isArchive=:flag");
            query.setParameter("user", user);
            query.setParameter("flag", 1);

            list = query.list();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return list;
    }


    @Override
    public void updateOrderDateTime(Long orderId, String dateTime) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" update Order set dateTime = :dateTime where orderId = :orderId");
            query.setParameter("dateTime", dateTime);
            query.setParameter("orderId", orderId);
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
    public void updateOrderIsArchive(Long orderId, Integer flag) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" update Order set isArchive = :flag where orderId = :orderId");
            query.setParameter("flag", flag);
            query.setParameter("orderId", orderId);
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
    public void updateOrderStatus(Long orderId, Integer orderStatus) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" update Order set orderStatus = :orderStatus where orderId = :orderId");
            query.setParameter("orderStatus", orderStatus);
            query.setParameter("orderId", orderId);
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
    public void updateOrderTotalPrice(Long orderId, BigDecimal totalPrice) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" update Order set totalPrice = :totalPrice where orderId = :orderId");
            query.setParameter("totalPrice", totalPrice);
            query.setParameter("orderId", orderId);
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
    public void updateOrderAddr(Long orderId, String deliveryAddress) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" update Order set deliveryAddress = :deliveryAddress where orderId = :orderId");
            query.setParameter("deliveryAddress", deliveryAddress);
            query.setParameter("orderId", orderId);
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
    public void updateTotalQty(Long orderId, Long productId, Integer qty) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Order order = (Order) session.get(Order.class, orderId);

            HashSet<Item> set = new HashSet<>(order.getItems());
            Iterator<Item> iterator = set.iterator();

            while (iterator.hasNext()) {
                Item item = iterator.next();
                if (item.getProduct().getProduct_id().equals(productId)) {
                    item.setTotalQuantity(qty);
                }
            }

            session.update(order);

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
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Order order = (Order) session.get(Order.class, orderId);

            HashSet<Item> set = new HashSet<>(order.getItems());
            Iterator<Item> iterator = set.iterator();

            while (iterator.hasNext()) {
                Item item = iterator.next();
                if (item.getProduct().getProduct_id().equals(productId)) {
                    iterator.remove();
                    session.delete(item);
                    session.evict(item);
                }
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }


    @Override
    public void deleteOrder(Long orderId) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" delete Order where orderId = :orderId");
            query.setParameter("orderId", orderId);
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
    public void deleteAllOrders() {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery("delete Order");
            int result = query.executeUpdate();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }
}


class TestOrder {

    public static void main(String[] args) {
        OrderDAOImpl or = new OrderDAOImpl();
        UserDAOImpl us = new UserDAOImpl();
        CategoryDAOImpl cat = new CategoryDAOImpl();
        ProductDAOImpl pr = new ProductDAOImpl();
        User user;
        Order obj1;

        //us.addUser("Вася", "abc@mail.ru", "123", "0951687125");
//        user = us.getUserAllFields(new Long(1));
//        user = us.getUserWithId(new Long(1));
//        Product product = pr.getProductWithId(new Long(2));
//
//        HashMap<Long, Integer> map1 = new HashMap<>();
//        map1.put(12L, 7);
//

//        HashMap<Long, Integer> map2 = new HashMap<>();
//        map2.put(10L, 1);
//        map2.put(11L, 2);

//        or.addOrder( "2016-05-27 15:33:00",  new BigDecimal(1000), "ул. Каннибала 135", 3L, map1 );
//        or.addOrder( "2016-06-08 15:01:00", new BigDecimal(700), "ул. КРАСНОФЛОТСКАЯ 45А", 3L, map2);

//        obj1 = or.getOrder(1L);
//        System.out.print(obj1.getDate_time()+" "+ obj1.getTotal_price()+" "+ obj1.getDelivery_address()+" ");

//        HashSet<Item> set = new HashSet<>(obj1.getItems()) ;

//        Iterator<Item> iterator = set.iterator();

//        while(iterator.hasNext()) {
//            Item item = iterator.next();
//            Product product = item.getProduct();
//
//            System.out.println(item.getTotal_quantity()+" " + product.getName()+" "+product.getPrice()+" "+product.getQuantity()+
//                " "+product.getDescription());
//        }


//        List<Product> list = obj1.get;
//        for (Product product : list) {
//            System.out.println(product.getProduct_id()+ " "+product.getName()+" "+product.getPrice()+" "+product.getQuantity()+
//                " "+product.getDescription());
//        }

//        List<Order> list = or.getAllOrders();
//        for (Order ob : list) {
//            System.out.println(ob.getDate_time() + " " + ob.getTotal_price() + " " +
//                    ob.getDelivery_address());
//        }

//        or.updateOrderDateTime(new Long(1), "2016-05-27 15:45:00");
//        or.updateOrderTotalQty(new Long(1), new Integer(20) );
//        or.updateOrderTotalPrice(new Long(1),  new BigDecimal(15000) );
//        or.updateOrderAddr(new Long(1), "ул. Василия 10" );
//        or.updateOrderStatus(37L, 1);
//        or.deleteOrder(12L);
//        or.deleteAllOrders();

//        List<Order> list = or.getOrdersByUser(3L);
//
//        List<Order> list = or.getAllOrders();
//        for (Order ob : list) {
//            System.out.print(ob.getDate_time() + " " + ob.getTotal_price() + " " +
//                    ob.getDelivery_address()+ " - ");
//
//
//            HashSet<Item> set = new HashSet<>(ob.getItems());
//            Iterator<Item> iterator = set.iterator();
//
//            while (iterator.hasNext()) {
//                Item item = iterator.next();
//                Product product = item.getProduct();
//
//                System.out.println(item.getTotal_quantity() + " " + product.getName() + " " + product.getPrice() + " " + product.getQuantity() +
//                        " " + product.getDescription());
//            }
//        }

//        or.updateTotalQty(12L, 10L, 21);

//        or.deleteItem(12L, 10L);
        HashSet<Item> items = new HashSet<>();

        Item item1 = new Item();
        Item item2 = new Item();
        Product prod1 = new Product("Laptop HP", new BigDecimal(1000), 2, "Pavilion-1020", new Category());
        prod1.setProduct_id(1L);
        Product prod2 = new Product("Laptop Acer", new BigDecimal(800), 1, "Aspire-1532", new Category());
        prod2.setProduct_id(2L);

        item1.setProduct(prod1);
        item1.setTotalQuantity(1);
        item2.setProduct(prod2);
        item2.setTotalQuantity(1);
        items.add(item1);
        items.add(item2);

        Product prod = new Product("Laptop HP", new BigDecimal(1000), 10, "Pavilion-1020", new Category());
        prod.setProduct_id(3L);
//
//        for (Item item:items) {
//            if(item.getProduct().equals(prod)){
//                System.out.println(true);
//            }
//        }

        BigDecimal totalPrice = new BigDecimal(0);

        for (Item item : items) {

            totalPrice = totalPrice.add(item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getTotalQuantity())));
        }
        System.out.println(totalPrice);
    }
}







































