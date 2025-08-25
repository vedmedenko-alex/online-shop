package com.solvd.online_shop.dao.interfaces;

import java.sql.SQLException;

import com.solvd.online_shop.models.Order;

public interface IOrderDao extends IGenericDao<Order>{
    // void addOrder(Order order) throws SQLException;

    // Order getOrderById(int id) throws SQLException;

    // List<Order> getAllOrders() throws SQLException;

    // void updateOrder(Order order) throws SQLException;

    // void deleteOrder(int id) throws SQLException;

    void deleteOrdersByUserId(int userId) throws SQLException;
}
