package com.database.dao.interfaces;

import com.database.domain.Category;

import java.util.List;

/**
 * @author  Shynkarenko Eduard
 *
 */

public interface CategoryDAO {

    public Long addCategory(String name, String description);
    public Integer countCategoryElements();
    public Category getCategory(Long categoryId);
    public Category getRandomCategory();
    public Category getFirstCategory();
    public Category getCategoryWithId(Long categoryId);
    public List<Category> getAllCategories();
    public Long getCategoryIdByName(String name);
    public void updateCategoryName(Long categoryId, String name);
    public void updateCategoryDesc(Long categoryId, String description);
    public void deleteCategory(Long categoryId);
    public void deleteAllCategories();
}
