package com.database.service;

import com.database.dao.implementation.CategoryDAOImpl;
import com.database.domain.Category;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.List;


/**
 * @author  Shynkarenko Eduard
 *
 */
@ManagedBean(name = "categoryService")
@ApplicationScoped
public class CategoryService implements Serializable {

    private CategoryDAOImpl cat = new CategoryDAOImpl();

    /**
     * Adds Category object into database table "category"
     * @param name  - "name" field in table
     * @param description - "description" field  in table
     * @return id of added Category object
     */
    public Long addCategory(String name, String description) {

        return cat.addCategory(name, description);

    }

    /**
     * Calculates the number of elements in table "category"
     * @return number of elements
     */
    public Integer countCategoryElements() {
        return cat.countCategoryElements();
    }

    /**
     * Retrieves Category object from database table "category"
     * @param categoryId - "id" field in  table
     * @return Category object with (id, name, description) fields
     */
    public Category getCategory(Long categoryId) {
        return cat.getCategory(categoryId);
    }

    /**
     *  Retrieves random Category object from database table "category"
     * @return Category object with (id, name, description) fields
     */
    public Category getRandomCategory() {
        return cat.getRandomCategory();
    }

    /**
     * Retrieves first Category object from database table "category"
     * @return Category object with (id, name, description) fields
     */
    public Category getFirstCategory() {
        return cat.getFirstCategory();
    }

    /**
     * Retrieves  Category proxy-object from database table "category"
     * @param categoryId - "id" field in table
     * @return Category proxy-object with only field "categoryId"
     */
    public Category getCategoryWithId(Long categoryId) {
        return cat.getCategoryWithId(categoryId);
    }

    /**
     * Retrieves all Category objects from database table "category"
     * @return List of Category objects
     */
    public List<Category> getAllCategories() {
        return cat.getAllCategories();
    }

    /**
     * Finds Category "id" field using "name" filed in table "category"
     * @param name - "name" field in table
     * @return "categoryId" field of Category object
     */
    public Long getCategoryIdByName(String name) {
        return cat.getCategoryIdByName(name);
    }

    /**
     * Updates "name" field in database table "category"
     * @param categoryId - "id" field in table
     * @param name - "name" field in table
     */
    public void updateCategoryName(Long categoryId, String name) {
        cat.updateCategoryName(categoryId, name);
    }

    /**
     * Updates "description" field in database table "category"
     * @param categoryId - "id" field in table
     * @param description - "description" field in table
     */
    public void updateCategoryDesc(Long categoryId, String description) {
        cat.updateCategoryDesc(categoryId, description);
    }

    /**
     * Deletes Category object from database table "category"
     * @param categoryId - "id" field in table
     */
    public void deleteCategory(Long categoryId) {
        cat.deleteCategory(categoryId);
    }

    /**
     * Deletes all Category objects from database table "category"
     */
    public void deleteAllCategories() {
        cat.deleteAllCategories();
    }

}
