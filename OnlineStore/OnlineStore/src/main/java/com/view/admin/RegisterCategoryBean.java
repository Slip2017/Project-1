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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 * Created by Администратор.
 */
@ManagedBean
@RequestScoped
public class RegisterCategoryBean implements Serializable {

    @ManagedProperty("#{categoryService}")
    private CategoryService categoryService;

    private Category category = new Category();

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String register() {
        // Calling Business Service
        categoryService.addReadyCategory(category);
        // Add message
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("The Category \"" + this.category.getName()
                        + "\" is Registered Successfully"));
        RequestContext.getCurrentInstance().closeDialog(null);
        return "";
    }

}
