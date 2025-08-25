package com.solvd.online_shop.services.impl;

import java.sql.SQLException;
import java.util.List;

import com.solvd.online_shop.dao.impl.OrderDao;
import com.solvd.online_shop.dao.impl.ProductDao;
import com.solvd.online_shop.models.Order;
import com.solvd.online_shop.models.Product;

public class OrderService {
    private final OrderDao orderDao;
    private final ProductDao productDao;

    public OrderService() {
        this.orderDao = new OrderDao();
        this.productDao = new ProductDao();
    }

    public void addOrder(Order order) throws SQLException {
        orderDao.add(order);
    }

    public Order getOrderById(int id) throws SQLException {
        return orderDao.getById(id);
    }

    public List<Order> getAllOrders() throws SQLException {
        return orderDao.getAll();
    }

    public List<Product> getProductsForOrder(int orderId) throws SQLException {

        return productDao.getAll();
    }

    public void updateOrder(Order order) throws SQLException {
        orderDao.update(order);
    }

    public void deleteOrder(int id) throws SQLException {
        orderDao.delete(id);
    }
}
