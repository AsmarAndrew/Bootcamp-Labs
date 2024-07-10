package org.yearup.data;

import org.yearup.models.Category;

import java.util.List;


/*
the DAO methods handle all the details of how to get categories from the database, add new categories, update existing ones, and delete categories
 */
public interface CategoryDao
{
    List<Category> getAllCategories();
    Category getById(int categoryId);
    Category create(Category category);
    void update(int categoryId, Category category);
    void delete(int categoryId);
}