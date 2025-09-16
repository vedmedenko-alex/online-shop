package com.solvd.online_shop.services.impl;

import java.sql.SQLException;
import java.util.List;

import com.solvd.online_shop.dao.impl.OrderDao;
import com.solvd.online_shop.dao.impl.ProductDao;
import com.solvd.online_shop.models.Order;
import com.solvd.online_shop.models.Product;
import com.solvd.online_shop.services.interfaces.IOrderItemService;

public class OrderItemService implements IOrderItemService{
    private final OrderDao orderDao;
    private final ProductDao productDao;

    public OrderItemService() {
        this.orderDao = new OrderDao();
        this.productDao = new ProductDao();
    }
    @Override
    public void addOrderItem(Order order, Product product)   {
        orderDao.add(order);
        productDao.add(product);
    }
    // @Override
    // public List<Product> getProductsInOrder(int orderId) throws SQLException {
    //     return productDao.getAll();
    // }

    public List<Product> getAll()   {
        return productDao.getAll();
    }
}
