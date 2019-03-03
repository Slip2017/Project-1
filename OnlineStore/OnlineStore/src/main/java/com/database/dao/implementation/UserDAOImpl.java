package com.database.dao.implementation;


import com.database.dao.interfaces.UserDAO;
import com.database.domain.User;
import org.hibernate.*;

import java.util.List;

/**
 * @author  Shynkarenko Eduard
 *
 */
public class UserDAOImpl implements UserDAO {

    @Override
    public Long addUser(String name, String email, String password, String phone) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        Long userId = null;
        try {
            tx = session.beginTransaction();

            User user = new User(name, email, password, phone);
            userId = (Long) session.save(user);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }
        return userId;
    }

    @Override
    public User getUser(Long userId) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        User user = null;
        try {
            tx = session.beginTransaction();

            Query query = session.createQuery("from User where userId = :userId");
            query.setParameter("userId", userId);
            List list = query.list();
            user = (User) list.get(0);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }
        return user;
    }

    @Override
    public boolean containsUserByMail(String email) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery("from User where email = :email");
            query.setParameter("email", email);
            List list = query.list();
            tx.commit();

            if(list.size()== 0){
                return false;
            }

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return true;
    }

    @Override
    public User getUserByMail(String email) {

        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        User user = null;
        try {
            tx = session.beginTransaction();

            Query query = session.createQuery("from User where email = :email");
            query.setParameter("email", email);
            List list = query.list();
            user = (User) list.get(0);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }
        return user;
    }

    @Override
    public boolean containsUserByPhone(String phone) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery("from User where phone = :phone");
            query.setParameter("phone", phone);
            List list = query.list();
            tx.commit();

            if(list.size()==0){
                return false;
            }

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }
        return true;
    }

    @Override
    public boolean containsUserByMailAndPass(String email, String password) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Query query = session.createQuery("from User where password = :pass and email=:email");
            query.setParameter("pass", password);
            query.setParameter("email", email);
            List list = query.list();
            tx.commit();

            if (list.size() == 0) {
                return false;
            }

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }
        return true;

    }

    @Override
    public User getUserWithId(Long userId) {

        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        User user = null;
        try {
            tx = session.beginTransaction();

            user = (User)session.load(User.class, userId);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        List<User> list = null;
        try {
            tx = session.beginTransaction();

            Query query = session.createQuery("from User");
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
    public void updateUserName(Long userId, String name) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" update User set name = :name where userId = :userId");
            query.setParameter("name", name);
            query.setParameter("userId", userId);
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
    public void updateUserMail(Long userId, String email) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" update User set email = :email where userId = :userId");
            query.setParameter("email", email);
            query.setParameter("userId", userId);
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
    public void updateUserPass(Long userId, String password) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" update User set password = :password where userId = :userId");
            query.setParameter("password", password);
            query.setParameter("userId", userId);
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
    public void updateUserPhone(Long userId, String phone) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" update User set phone = :phone where userId = :userId");
            query.setParameter("phone", phone);
            query.setParameter("userId", userId);
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
    public void deleteUser(Long userId) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" delete User where userId = :userId");
            query.setParameter("userId", userId);
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
    public void deleteAllUsers() {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery("delete User");
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

class TestUser {

    public static void main(String[] args) {
        UserDAOImpl user = new UserDAOImpl();

        user.addUser("Петя3", "ewq@mail.ru", "741123", "32548423");
//        User obj = user.getUserAllFields(new Long(1));
//
//        HashSet<Order> set = new HashSet<>(obj.getOrders());
//
//        Iterator<Order> iterator = set.iterator();
//
//        while (iterator.hasNext()) {
//            Order order = iterator.next();
//            System.out.print(order.getDate_time() + " " + order.getTotal_quantity() + " " + order.getTotal_price() + " " +
//                    order.getDelivery_address() + " - ");
//        }
//            HashSet<Product> set2 = new HashSet<>(order.getProducts());
//
//            Iterator<Product> iterator2 = set2.iterator();
//
//            while (iterator2.hasNext()) {
//                Product product = iterator2.next();
//            System.out.print(product.getProduct_id()+ " "+product.getName()+" "+product.getPrice()+" "+product.getQuantity()+
//                " "+product.getDescription()+" - "+product.getCategory().getName()+" "+product.getCategory().getDescription());
//
//            }
//
//            System.out.println();
//        }

//        System.out.println(obj.getUser_id()+" "+obj.getName()+" " + obj.getEmail()+" " +obj.getPassword()+" "+obj.getPhone());
//       List<User> list =  user.getAllUsers();
//        for (User obj : list) {
//            System.out.println(obj.getUser_id()+" "+obj.getName()+" " + obj.getEmail()+" " +obj.getPassword()+" "+obj.getPhone());
//        }
//        user.updateUserMail(new Long(1), "qwerty@mail.ru");
//        user.updateUserName(new Long(1), "Николай");
//        user.updateUserPhone(new Long(1), "0665478631");
//        user.deleteUser(new Long(2));
//        user.deleteAllUsers();
//        User obj = user.getUser(55L);
//        String pass = obj.getPassword();
//
//        System.out.println(obj.getPassword());

    }
}



























