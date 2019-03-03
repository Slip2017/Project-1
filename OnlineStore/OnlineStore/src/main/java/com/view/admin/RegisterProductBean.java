/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view.admin;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import com.database.domain.Category;
import com.database.domain.Product;
import com.database.service.CategoryService;
import com.database.service.ProductService;
import java.util.Enumeration;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 * Created by Администратор.
 */
@ManagedBean
@RequestScoped
public class RegisterProductBean implements Serializable {

    @ManagedProperty("#{productService}")
    private ProductService productService;

    @ManagedProperty(value = "#{categoryTableBean}")
    private CategoryTableBean categoryTable;

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public CategoryTableBean getCategoryTable() {
        return categoryTable;
    }

    public void setCategoryTable(CategoryTableBean categoryTable) {
        this.categoryTable = categoryTable;
    }

    private Product product = new Product();

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String register() {
        // Calling Business Service
        //product.setCategory(categoryTable.getCategory());
        categoryTable.getCategory().getProducts().add(product);
        productService
                .addReadyProduct(product,
                        categoryTable.getCategory().getCategoryId());
        // Add message
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("The Category \"" + this.product.getName()
                        + "\" is Registered Successfully"));
        RequestContext.getCurrentInstance().closeDialog(null);
        return "";
    }

}
