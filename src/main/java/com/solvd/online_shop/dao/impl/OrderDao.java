package com.solvd.online_shop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.solvd.online_shop.connection.ConnectionPool;
import com.solvd.online_shop.dao.interfaces.IOrderDao;
import com.solvd.online_shop.models.Order;

public class OrderDao implements IOrderDao {

    private static final String INSERT_ORDER = "INSERT INTO Orders (user_id, order_date, status, total_amount) VALUES (?, ?, ?, ?)";
    private static final String GET_ORDER_BY_ID = "SELECT * FROM Orders WHERE order_id = ?";
    private static final String GET_ALL_ORDERS = "SELECT * FROM Orders";
    private static final String UPDATE_ORDER = "UPDATE Orders SET user_id = ?, order_date = ?, status = ?, total_amount = ? WHERE order_id = ?";
    private static final String DELETE_ORDER = "DELETE FROM Orders WHERE order_id = ?";
    private static final String DELETE_ORDERS_BY_USER_ID = "DELETE FROM Orders WHERE user_id = ?";
    ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public void add(Order order) throws SQLException {
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(INSERT_ORDER)) {
            stmt.setInt(1, order.getUserId());
            stmt.setString(2, order.getOrderDate());
            stmt.setString(3, order.getStatus());
            stmt.setDouble(4, order.getTotalAmount());
            stmt.executeUpdate();
        }
    }

    @Override
    public Order getById(int id) throws SQLException {
        Order order = null;
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(GET_ORDER_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                order = new Order(rs.getInt("order_id"), rs.getInt("user_id"), rs.getString("order_date"),
                        rs.getString("status"), rs.getDouble("total_amount"));
            }
        }
        return order;
    }

    @Override
    public List<Order> getAll() throws SQLException {
        List<Order> orders = new ArrayList<>();
        try (Connection conn = pool.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(GET_ALL_ORDERS)) {
            while (rs.next()) {
                orders.add(new Order(rs.getInt("order_id"), rs.getInt("user_id"), rs.getString("order_date"),
                        rs.getString("status"), rs.getDouble("total_amount")));
            }
        }
        return orders;
    }

    @Override
    public void update(Order order) throws SQLException {
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(UPDATE_ORDER)) {
            stmt.setInt(1, order.getUserId());
            stmt.setString(2, order.getOrderDate());
            stmt.setString(3, order.getStatus());
            stmt.setDouble(4, order.getTotalAmount());
            stmt.setInt(5, order.getOrderId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(DELETE_ORDER)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteOrdersByUserId(int userId) throws SQLException {
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(DELETE_ORDERS_BY_USER_ID)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        }
    }

}
