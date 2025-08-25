package com.solvd.online_shop.services.impl;

import java.sql.SQLException;
import java.util.List;

import com.solvd.online_shop.dao.impl.CategoryDao;
import com.solvd.online_shop.dao.impl.ProductDao;
import com.solvd.online_shop.models.Category;
import com.solvd.online_shop.models.Product;

public class CategoryService {
    private final CategoryDao categoryDao;
    private final ProductDao productDao;

    public CategoryService() {
        this.categoryDao = new CategoryDao();
        this.productDao = new ProductDao();
    }

    public void addCategory(Category category) throws SQLException {
        categoryDao.add(category);
    }

    public Category getCategoryById(int id) throws SQLException {
        return categoryDao.getById(id);
    }

    public List<Category> getAllCategories() throws SQLException {
        return categoryDao.getAll();
    }

    public List<Product> getProductsByCategory(int categoryId) throws SQLException {

        return productDao.getAll();
    }

    public void updateCategory(Category category) throws SQLException {
        categoryDao.update(category);
    }

    public void deleteCategory(int id) throws SQLException {
        categoryDao.delete(id);
    }
}
