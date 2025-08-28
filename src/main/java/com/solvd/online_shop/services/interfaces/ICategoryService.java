package com.solvd.online_shop.services.interfaces;

import com.solvd.online_shop.models.Category;
import com.solvd.online_shop.models.Product;
import java.sql.SQLException;

import java.util.List;

public interface ICategoryService extends IGenericService<Category> {

    // List<Product> getProductsByCategory(int categoryId) throws SQLException;
}
