package com.view.web;

import com.database.domain.Product;
import com.database.service.CategoryService;
import com.database.service.ProductService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;


/**
 * @author  Shynkarenko Eduard
 *
 */

@ManagedBean(eager = true)
@SessionScoped
public class ProductTableBean implements Serializable {
    static int count1 = 0;
    static int count2 = 0;

    @ManagedProperty("#{categoryService}")
    private CategoryService catService;
    @ManagedProperty("#{productService}")
    private ProductService prodService;
    private Long currentCategoryId;
    private List<Product> active;

    public ProductTableBean() {
    }

    @PostConstruct
    public void init() {

//        currentCategoryId = catService.getFirstCategory().getCategoryId();

    }

    public void setCatService(CategoryService catService) {
        this.catService = catService;
    }

    public void setProdService(ProductService prodService) {
        this.prodService = prodService;
    }

    public CategoryService getCatService() {
        return catService;
    }

    public ProductService getProdService() {
        return prodService;
    }

    public List<Product> getActive() {
        return active;
    }

    public void installActiveProducts() {

        active = prodService.getAllActiveProductsByCategoryId(currentCategoryId);
    }

    public void installActiveProducts(Long id) {

        active = prodService.getAllActiveProductsByCategoryId(id);
    }

    public Long getCurrentCategoryId() {
        return currentCategoryId;
    }

    public void setCurrentCategoryId(Long currentCategoryId) {
        this.currentCategoryId = currentCategoryId;
    }
}
