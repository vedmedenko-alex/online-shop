package com.solvd.online_shop.services.impl;

import java.sql.SQLException;
import java.util.List;

import com.solvd.online_shop.dao.impl.CategoryDao;
import com.solvd.online_shop.dao.impl.ProductDao;
import com.solvd.online_shop.models.Category;
import com.solvd.online_shop.models.Product;
import com.solvd.online_shop.services.interfaces.ICategoryService;

public class CategoryService implements ICategoryService {

    private final CategoryDao categoryDao;
    private final ProductDao productDao;

    public CategoryService() {
        this.categoryDao = new CategoryDao();
        this.productDao = new ProductDao();
    }

    @Override
    public void add(Category category)   {
        categoryDao.add(category);
    }

    @Override
    public Category getById(int id)   {
        return categoryDao.getById(id);
    }

    @Override
    public List<Category> getAll()   {
        return categoryDao.getAll();
    }

    // @Override
    // public List<Product> getProductsByCategory(int categoryId) throws SQLException {
    //     return productDao.getAll();
    // }

    @Override
    public void update(Category category)   {
        categoryDao.update(category);
    }

    @Override
    public void delete(int id)   {
        categoryDao.delete(id);
    }
}
