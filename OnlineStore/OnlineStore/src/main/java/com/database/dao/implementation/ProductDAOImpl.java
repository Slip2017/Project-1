package com.database.dao.implementation;

import com.database.dao.interfaces.ProductDAO;
import com.database.domain.Category;
import com.database.domain.Product;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author  Shynkarenko Eduard
 *
 */
public class ProductDAOImpl implements ProductDAO {

    static int count = 0;

    @Override
    public Long addProduct(String name, BigDecimal price, Integer quantity, String description, Long categoryId) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        Long productId = null;

        try {
            tx = session.beginTransaction();

            Category cat = (Category) session.load(Category.class, categoryId);
            Product pr = new Product(name, price, quantity, description, cat);
            productId = (Long) session.save(pr);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }
        return productId;
    }

    @Override
    public Product getProduct(Long product_id) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        Product pr = null;
        try {
            tx = session.beginTransaction();

            Query query = session.createQuery("from Product where productId = :product_id");
            query.setParameter("product_id", product_id);
            List list = query.list();
            pr = (Product) list.get(0);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return pr;
    }

    @Override
    public Product getProductWithId(Long product_id) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        Product pr = null;
        try {
            tx = session.beginTransaction();

            pr = (Product) session.load(Product.class, product_id);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return pr;
    }

    @Override
    public List<Product> getProductsByCategory(Long categoryId) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        List<Product> list = null;

        try {
            tx = session.beginTransaction();

            Category cat = (Category) session.load(Category.class, categoryId);
            Query query = session.createQuery("from Product where category = :category");
            query.setParameter("category", cat);
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
    public List<Product> getAllActiveProductsByCategoryId(Long categoryId) {

        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        List<Product> list = null;

        try {
            tx = session.beginTransaction();
//            count++;
//            System.out.println("getAllActiveProductsByCategoryId - "+categoryId+" - "+count);
            Category cat = (Category) session.get(Category.class, categoryId);
            Query query = session.createQuery("from Product where category = :category and isDeleted =:flag");
            query.setParameter("category", cat);
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
    public List<Product> getAllProducts() {

        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        List<Product> list = null;
        try {
            tx = session.beginTransaction();

            Query query = session.createQuery("from Product");
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
    public List<Product> getAllDeletedProducts() {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        List<Product> list = null;
        try {
            tx = session.beginTransaction();

            Query query = session.createQuery("from Product where isDeleted =:flag");
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
    public List<Product> getAllActiveProducts() {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        List<Product> list = null;
        try {
            tx = session.beginTransaction();

            Query query = session.createQuery("from Product where isDeleted =:flag");
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
    public void updateProductName(Long productId, String name) {

        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" update Product set name = :name where productId = :productId");
            query.setParameter("name", name);
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
    public void updateProductIsDeleted(Long productId, Integer flag) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" update Product set isDeleted = :flag where productId = :productId");
            query.setParameter("flag", flag);
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
    public void updateProductPrice(Long productId, BigDecimal price) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" update Product set price = :price where productId = :productId");
            query.setParameter("price", price);
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
    public void updateProductQty(Long productId, Integer quantity) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" update Product set quantity = :quantity where productId = :productId");
            query.setParameter("quantity", quantity);
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
    public void updateProductDesc(Long productId, String description) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" update Product set description = :description where productId = :productId");
            query.setParameter("description", description);
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
    public void updateProductCategory(Long productId, Category catedory_id) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Category cat = (Category) session.load(Category.class, catedory_id);
            Query query = session.createQuery(" update Product set category = :cat where productId = :productId");
            query.setParameter("cat", cat);
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
    public void deleteProduct(Long productId) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" delete Product where productId = :productId");
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
    public void deleteAllProducts() {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery("delete Product");
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

class TestProduct {
    public static void main(String[] args) {
        CategoryDAOImpl cat = new CategoryDAOImpl();
        ProductDAOImpl pr = new ProductDAOImpl();
//        Long id  = cat.addCategory("Computer", "траляля");
//        Category obj = cat.getCategoryAllFields("Computer");
//        Category obj = cat.getCategoryWithId(new Long(1));
        pr.addProduct("Laptop HP", new BigDecimal(1000), 10, "Pavilion-1020", 6L);
        pr.addProduct("Laptop Acer", new BigDecimal(800), 5,  "Aspire-1532", 6L);
        pr.addProduct("Laptop Toshiba", new BigDecimal(1250), 10, "NPE-1543", 6L);
        pr.addProduct("Laptop Sony", new BigDecimal(2000), 3, "VAIO-12", 6L);
//        Product product = pr.getProduct(10L);
//
//        System.out.println(product.getProduct_id()+ " "+product.getName()+" "+product.getPrice()+" "+product.getQuantity()+
//                " "+product.getDescription()+" "+product.getCategory().getName());
//        List<Product> list = pr.getAllProducts();
//        BigDecimal total = new BigDecimal(0);
//        for (Product prod : list) {
//            total = total.add(prod.getPrice());
//        }
//        System.out.println(total);
//        List<Product> list = pr.getProductsByCategory(8L);
//        for (Product product : list) {
//            System.out.println(product.getProduct_id()+ " "+product.getName()+" "+product.getPrice()+" "+product.getQuantity()+
//                " "+product.getDescription());
//        }

//       HashSet<Product> set = new HashSet<>(obj.getProducts()) ;
//        Iterator<Product> iterator = set.iterator();
//        while(iterator.hasNext()) {
//            Product product = iterator.next();
//            System.out.println(product.getProduct_id()+ " "+product.getName()+" "+product.getPrice()+" "+product.getQuantity()+
//                " "+product.getDescription());
//        }

        //pr.updateProductName(new Long(1), "Laptop Lenovo");
        //pr.updateProductPrice(new Long(1), new BigDecimal(1200) );
        //pr.updateProductQty(new Long(1), new Integer(8));
        //pr.updateProductDesc(new Long(1), "Yoga-Z015");
//        pr.deleteProduct(3L);
        //pr.deleteAllProducts();

//        pr.updateProductCategory(new Long(2), obj);

//        List<Product> list = pr.getProductsByCategory(obj);
//
//        for (Product product : list) {
//            System.out.println(product.getProduct_id()+ " "+product.getName()+" "+product.getPrice()+" "+product.getQuantity()+
//                " "+product.getDescription());
//        }


    }
}


























