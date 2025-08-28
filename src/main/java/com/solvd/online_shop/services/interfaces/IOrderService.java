package com.solvd.online_shop.services.interfaces;

import com.solvd.online_shop.models.Order;
import com.solvd.online_shop.models.Product;
import java.sql.SQLException;

import java.util.List;

public interface IOrderService extends IGenericService<Order> {

    // List<Product> getProductsForOrder(int orderId) throws SQLException;
}
