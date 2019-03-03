package com.database.service;

import com.database.dao.implementation.ProductDAOImpl;
import com.database.domain.Category;
import com.database.domain.Product;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


/**
 * @author  Shynkarenko Eduard
 *
 */

@ManagedBean(name = "productService")
@ApplicationScoped
public class ProductService implements Serializable {

    private ProductDAOImpl pr = new ProductDAOImpl();

    /**
     *  Adds Product object into database table "product"
     * @param name -  "name" field in table
     * @param price -  "price" field in table
     * @param quantity -  "quantity" field in table
     * @param description - "description" field in table
     * @param categoryId - "category_id" field in table
     * @return id of added Order object
     */
    public Long addProduct(String name, BigDecimal price, Integer quantity, String description, Long categoryId) {
        return pr.addProduct(name, price, quantity, description, categoryId);
    }

    /**
     *  Retrieves Order object from database table "product"
     * @param productId - "product_id" field in table
     * @return Product object with (productId, isDeleted, name, price, quantity, description) fields
     */
    public Product getProduct(Long productId) {
        return pr.getProduct(productId);
    }

    /**
     * Retrieves Product proxy-object from database table "product"
     * @param productId - "product_id" field in table
     * @return Category proxy-object with only field "productId"
     */
    public Product getProductWithId(Long productId) {
        return pr.getProductWithId(productId);
    }

    /**
     * Retrieves all Product objects from database table "product" for Category
     * @param categoryId - "category_id" field in table
     * @return List of Product objects
     */
    public List<Product> getProductsByCategory(Long categoryId) {
        return pr.getProductsByCategory(categoryId);
    }

    /**
     * Retrieves all active Product objects from database table "product" for Category
     * @return List of Product objects
     */
    public List<Product> getAllActiveProductsByCategoryId(Long categoryId){
        return pr.getAllActiveProductsByCategoryId(categoryId);
    }

    /**
     * Retrieves all Product objects from database table "product"
     * @return List of Product objects
     */
    public List<Product> getAllProducts() {
        return pr.getAllProducts();
    }

    /**
     * Retrieves all "deleted" Product objects from database table "product"
     * @return List of Product objects
     */
    public List<Product> getAllDeletedProducts() {
        return pr.getAllDeletedProducts();
    }

    /**
     * Retrieves all active Product objects from database table "product"
     * @return List of Product objects
     */
    public List<Product> getAllActiveProducts() {
        return pr.getAllActiveProducts();
    }



    /**
     * Updates "name" field in database table "product".
     * @param productId - "product_id" field in table
     * @param name - "name" field in table
     */
    public void updateProductName(Long productId, String name) {
        pr.updateProductName(productId, name);
    }


    /**
     *  Updates "is_deleted" field in database table "product".
     * @param productId - "product_id" field in table
     * @param flag:  0 - "Активный", 1 - "Удален". Default 0
     */
    public void updateProductIsDeleted(Long productId, Integer flag) {
        pr.updateProductIsDeleted(productId, flag);
    }

    /**
     *  Updates "price" field in database table "product".
     * @param productId - "product_id" field in table
     * @param price - "price" field in table
     */
    public void updateProductPrice(Long productId, BigDecimal price){
        pr.updateProductPrice(productId, price);
    }

    /**
     * Updates "quantity" field in database table "product".
     * @param productId - "product_id" field in table
     * @param quantity - "quantity" field in table
     */
    public void updateProductQty(Long productId, Integer quantity){
        pr.updateProductQty(productId, quantity);
    }

    /**
     *  Updates "description" field in database table "product".
     * @param productId - "product_id" field in table
     * @param description - "description" field in table
     */
    public void updateProductDesc(Long productId, String description){
        pr.updateProductDesc(productId, description);
    }

    /**
     *  Updates  "category_id" field in database table "product". Changes product category
     * @param productId - "product_id" field in table
     * @param category_id - "category_id" field in table
     */
    public void updateProductCategory(Long productId, Category category_id){
        pr.updateProductCategory(productId, category_id);
    }

    /**
     * Deletes Product object from database table "product"
     * @param productId - "product_id" field in table
     */
    public void deleteProduct(Long productId){
        pr.deleteProduct(productId);
    }

    /**
     * Deletes all Product objects from database table "product"
     */
    public void deleteAllProducts(){
        pr.deleteAllProducts();
    }
}
