package com.solvd.online_shop.services.impl;

import java.sql.SQLException;
import java.util.List;

import com.solvd.online_shop.dao.impl.CartItemDao;
import com.solvd.online_shop.dao.impl.DiscountDao;
import com.solvd.online_shop.dao.impl.OrderItemDao;
import com.solvd.online_shop.dao.impl.ProductDao;
import com.solvd.online_shop.models.Product;

public class ProductService {
    private final ProductDao productDao;
    private final OrderItemDao orderItemDao;
    private final CartItemDao cartItemDao;
    private final DiscountDao discountDao;

    public ProductService() {
        this.productDao = new ProductDao();
        this.orderItemDao = new OrderItemDao();
        this.cartItemDao = new CartItemDao();
        this.discountDao = new DiscountDao();
    }

    public void addProduct(Product product) throws SQLException {
        productDao.add(product);
    }

    public Product getProductById(int id) throws SQLException {
        return productDao.getById(id);
    }

    public List<Product> getAllProducts() throws SQLException {
        return productDao.getAll();
    }

    public void updateProduct(Product product) throws SQLException {
        productDao.update(product);
    }

    public void deleteProduct(int id) throws SQLException {
        orderItemDao.deleteOrderItemsByProductId(id);
        cartItemDao.deleteCartItemsByProductId(id);
        discountDao.deleteDiscountsByProductId(id);
        productDao.delete(id);
    }
}
