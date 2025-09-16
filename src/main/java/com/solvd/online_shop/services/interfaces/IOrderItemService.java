package com.solvd.online_shop.services.interfaces;

import com.solvd.online_shop.models.Order;
import com.solvd.online_shop.models.Product;
import java.sql.SQLException;

import java.util.List;

public interface IOrderItemService {

    void addOrderItem(Order order, Product product);

    // List<Product> getProductsInOrder(int orderId) throws SQLException;
}
