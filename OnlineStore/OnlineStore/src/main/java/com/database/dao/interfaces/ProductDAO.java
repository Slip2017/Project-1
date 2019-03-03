package com.database.dao.interfaces;

import com.database.domain.Category;
import com.database.domain.Product;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author  Shynkarenko Eduard
 *
 */

public interface ProductDAO {

    public Long addProduct(String name, BigDecimal price, Integer quantity, String description, Long categoryId);
    public Product getProduct(Long productId);
    public Product getProductWithId(Long productId);
    public List<Product> getProductsByCategory(Long categoryId);
    public List<Product> getAllActiveProductsByCategoryId(Long categoryId);
    public List<Product> getAllProducts();
    public List<Product> getAllDeletedProducts();
    public List<Product> getAllActiveProducts();
    public void updateProductName(Long productId, String name);
    public void updateProductIsDeleted(Long productId, Integer flag);
    public void updateProductPrice(Long productId, BigDecimal price);
    public void updateProductQty(Long productId, Integer quantity);
    public void updateProductDesc(Long productId, String description);
    public void updateProductCategory(Long productId, Category categoryId);
    public void deleteProduct(Long productId);
    public void deleteAllProducts();

}
