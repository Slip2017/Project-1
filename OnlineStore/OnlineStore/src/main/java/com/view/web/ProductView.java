package com.view.web;

import com.database.domain.Product;
import com.database.service.ProductService;

import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import java.io.Serializable;


/**
 * @author  Shynkarenko Eduard
 *
 */

@ManagedBean
@ViewScoped
public class ProductView implements Serializable {

    @ManagedProperty("#{productService}")
    private ProductService prodService;
    private Product product;
    private Long productId;

    @PostConstruct
    public void init() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public ProductService getProdService() {
        return prodService;
    }

    public void setProdService(ProductService prodService) {
        this.prodService = prodService;
    }

    public Product getProduct() {
        if (product == null) {
            product = prodService.getProduct(productId);
        }

        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
