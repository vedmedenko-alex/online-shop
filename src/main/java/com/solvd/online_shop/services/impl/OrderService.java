package com.solvd.online_shop.services.impl;

import java.sql.SQLException;
import java.util.List;

import com.solvd.online_shop.dao.impl.OrderDao;
import com.solvd.online_shop.dao.impl.ProductDao;
import com.solvd.online_shop.models.Order;
import com.solvd.online_shop.models.Product;
import com.solvd.online_shop.services.interfaces.IOrderService;

public class OrderService implements IOrderService{
    private final OrderDao orderDao;
    private final ProductDao productDao;

    public OrderService() {
        this.orderDao = new OrderDao();
        this.productDao = new ProductDao();
    }
    @Override
    public void add(Order order)   {
        orderDao.add(order);
    }
    @Override
    public Order getById(int id)   {
        return orderDao.getById(id);
    }
    @Override
    public List<Order> getAll()   {
        return orderDao.getAll();
    }
    // @Override
    // public List<Product> getProductsForOrder(int orderId) throws SQLException {

    //     return productDao.getAll();
    // }
    @Override
    public void update(Order order)   {
        orderDao.update(order);
    }
    @Override
    public void delete(int id)   {
        orderDao.delete(id);
    }
}
