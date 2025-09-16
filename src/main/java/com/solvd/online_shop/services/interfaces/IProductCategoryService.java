package com.solvd.online_shop.services.interfaces;

import com.solvd.online_shop.models.Category;
import com.solvd.online_shop.models.Product;
import java.sql.SQLException;

import java.util.List;

public interface IProductCategoryService {

    void addProductToCategory(Product product, Category category);

    List<Product> getProductsByCategory(int categoryId);

    List<Category> getAllCategories();
}
