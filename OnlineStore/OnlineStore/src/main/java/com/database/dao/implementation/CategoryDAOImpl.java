package com.database.dao.implementation;

import com.database.dao.interfaces.CategoryDAO;
import com.database.domain.Category;
import org.hibernate.*;

import java.util.Iterator;
import java.util.List;

/**
 * @author  Shynkarenko Eduard
 *
 */

public class CategoryDAOImpl implements CategoryDAO {
    static int count = 0;

    @Override
    public Long addCategory(String name, String description) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        Long categoryId = null;
        try {
            tx = session.beginTransaction();

            Category cat = new Category(name, description);
            categoryId = (Long) session.save(cat);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            try {
                HibernateSessionFactory.closeSession();
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }
        return categoryId;
    }

    @Override
    public Integer countCategoryElements() {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        Long res = null;
        try {
            tx = session.beginTransaction();

            Query query = session.createQuery("select count(*) from Category ");
            res = (Long) query.uniqueResult();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return res.intValue();
    }

    @Override
    public Category getCategory(Long categoryId) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        Category cat = null;
        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" from Category where categoryId = :category_id");
            query.setParameter("category_id", categoryId);
            List list = query.list();
            cat = (Category) list.get(0);


            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return cat;
    }

    @Override
    public Category getRandomCategory() {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        Category cat = null;
        try {
            tx = session.beginTransaction();


            SQLQuery query = session.createSQLQuery("SELECT t.*\n" +
                    "FROM category AS t \n" +
                    "JOIN (SELECT ((SELECT MIN(category_id) - 1 FROM category) + RAND() * (SELECT MAX(category_id) - MIN(category_id) \n" +
                    "               FROM category)) AS id) AS rand\n" +
                    "WHERE t.category_id >= rand.id\n" +
                    "ORDER BY t.category_id\n" +
                    "LIMIT 1").addEntity(Category.class);

            List list = query.list();
            cat = (Category) list.get(0);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return cat;

    }

    @Override
    public Category getFirstCategory() {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        Category cat = null;
        try {
            tx = session.beginTransaction();

            SQLQuery query = session.createSQLQuery("SELECT * FROM category LIMIT 1").addEntity(Category.class);

            List list = query.list();
            cat = (Category) list.get(0);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return cat;
    }

    @Override
    public Category getCategoryWithId(Long categoryId) {

        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        Category cat = null;
        try {
            tx = session.beginTransaction();

            cat = (Category) session.load(Category.class, categoryId);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }
        return cat;
    }

    @Override
        public List<Category> getAllCategories() {

        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        List list = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from Category");
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
    public Long getCategoryIdByName(String name) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        Long categoryId = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery("select categoryId from Category where name = :name");
            query.setParameter("name", name);
            List list = query.list();
            Iterator itr = list.iterator();
            while (itr.hasNext()) {
                categoryId = (Long) itr.next();
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return categoryId;
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" delete Category where categoryId = :categoryId");
            query.setParameter("categoryId", categoryId);
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
    public void deleteAllCategories() {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery("delete Category");
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
    public void updateCategoryName(Long categoryId, String name) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" update Category set name = :name where categoryId = :category_id");
            query.setParameter("name", name);
            query.setParameter("category_id", categoryId);
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
    public void updateCategoryDesc(Long categoryId, String description) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" update Category set description = :description where categoryId = :category_id");
            query.setParameter("description", description);
            query.setParameter("category_id", categoryId);
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

class TestCat {
    public static void main(String[] args) {
        CategoryDAOImpl o = new CategoryDAOImpl();
        Long id  = o.addCategory("Computer", "HP");
        Long id2  = o.addCategory("Sport", "bodybuilding");
//        System.out.println(id);
//        Category cat = o.getCategoryAllFields("Computer");

        //System.out.println(cat.getCategory_id()+" "+ cat.getName()+" "+cat.getDescription());
//        o.deleteCategory("Computer");

//        Long id = o.getCategoryIdByName("Computer");
        //System.out.println(id);
        //Long id1  = o.addCategory("Phone", "Nokia");
        // Long id2  = o.addCategory("Sports", "Gym");
//        List<Category> list = o.getAllCategories();
//        for (Category cat :list) {
//            System.out.println(cat.getCategory_id()+" "+ cat.getName());
//        }
        // o.updateCategoryName(new Long(2), "Zalupa");
        //o.updateCategoryDesc(new Long(2), "Pizda");
//        o.deleteAllCategories();
//        List<Product> list = o.getCategoryProducts(new Long(1));
//        Iterator<Product> iterator = list.iterator();
//
//        while (iterator.hasNext()) {
//            Product product = iterator.next();
//            System.out.println(product.getProduct_id() + " " + product.getName() + " " + product.getPrice() + " " + product.getQuantity() +
//                    " " + product.getDescription());
//        }

//        Integer l = o.countCategoryElements();
//        System.out.println(l);
//        Category cat = o.getFirstCategory();
//        System.out.println(cat.getCategory_id()+" "+ cat.getName()+" "+cat.getDescription());
    }
}
