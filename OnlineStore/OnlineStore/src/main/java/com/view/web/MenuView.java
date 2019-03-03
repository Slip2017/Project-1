package com.view.web;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.database.domain.Category;
import com.database.service.CategoryService;
import com.database.service.ProductService;
import org.primefaces.model.menu.*;

import java.io.Serializable;
import java.util.List;


/**
 * @author  Shynkarenko Eduard
 *
 */

@ManagedBean(eager = true)
@SessionScoped
public class MenuView implements Serializable {

    static int count1 = 0;
    static int count2 = 0;
    private MenuModel model;

    @ManagedProperty("#{categoryService}")
    private CategoryService cat;
    @ManagedProperty("#{productService}")
    private ProductService prod;

    private List<Category> list;

    public MenuView() {

    }

    @PostConstruct
    public void initialize() {
        model = new DefaultMenuModel();
        DefaultMenuItem item;
        DefaultSubMenu subMenu = new DefaultSubMenu("Каталог товаров");

        list = cat.getAllCategories();

        for (Category cat : list) {
            item = new DefaultMenuItem();
            item.setValue(cat.getName());

            item.setOutcome("main?id="+cat.getCategoryId());
            item.setUpdate(":form:products");
            subMenu.addElement(item);
        }

        model.addElement(subMenu);
    }

    public MenuModel getModel() {
        return model;
    }

    public CategoryService getCat() {
        return cat;
    }

    public void setCat(CategoryService categoryService) {
        this.cat = categoryService;
    }

    public ProductService getProd() {
        return prod;
    }

    public void setProd(ProductService prod) {
        this.prod = prod;
    }

    public List<Category> getList() {
        return list;
    }

    public void setList(List<Category> list) {
        this.list = list;
    }

}




