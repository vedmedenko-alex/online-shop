package com.solvd.online_shop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.solvd.online_shop.connection.ConnectionPool;
import com.solvd.online_shop.dao.interfaces.IOrderDao;
import com.solvd.online_shop.models.Order;

public class OrderDao implements IOrderDao {
    private static final Logger logger = Logger.getLogger(OrderDao.class.getName());

    private static final String INSERT_ORDER = "INSERT INTO Orders (user_id, order_date, status, total_amount) VALUES (?, ?, ?, ?)";
    private static final String GET_ORDER_BY_ID = "SELECT * FROM Orders WHERE order_id = ?";
    private static final String GET_ALL_ORDERS = "SELECT * FROM Orders";
    private static final String UPDATE_ORDER = "UPDATE Orders SET user_id = ?, order_date = ?, status = ?, total_amount = ? WHERE order_id = ?";
    private static final String DELETE_ORDER = "DELETE FROM Orders WHERE order_id = ?";
    private static final String DELETE_ORDERS_BY_USER_ID = "DELETE FROM Orders WHERE user_id = ?";
    private final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public void add(Order order) {
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(INSERT_ORDER)) {
            stmt.setInt(1, order.getUserId());
            stmt.setString(2, order.getOrderDate());
            stmt.setString(3, order.getStatus());
            stmt.setDouble(4, order.getTotalAmount());
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error adding order by ID: " + order, e);
            throw new RuntimeException("Database error while adding order ", e);
        }
    }

    @Override
    public Order getById(int id) {
        Order order = null;
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(GET_ORDER_BY_ID)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    order = new Order(rs.getInt("order_id"), rs.getInt("user_id"), rs.getString("order_date"),
                            rs.getString("status"), rs.getDouble("total_amount"));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching order by ID: " + id, e);
            throw new RuntimeException("Database error while fetching order by ID", e);
        }
        return order;
    }

    @Override
    public List<Order> getAll()  {
        List<Order> orders = new ArrayList<>();
        try (Connection conn = pool.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(GET_ALL_ORDERS)) {
            while (rs.next()) {
                orders.add(new Order(rs.getInt("order_id"), rs.getInt("user_id"), rs.getString("order_date"),
                        rs.getString("status"), rs.getDouble("total_amount")));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching all orders", e);
            throw new RuntimeException("Database error while fetching all orders", e);
        }
        return orders;
    }

    @Override
    public void update(Order order) {
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(UPDATE_ORDER)) {
            stmt.setInt(1, order.getUserId());
            stmt.setString(2, order.getOrderDate());
            stmt.setString(3, order.getStatus());
            stmt.setDouble(4, order.getTotalAmount());
            stmt.setInt(5, order.getOrderId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating user: " + order, e);
            throw new RuntimeException("Database error while updating order", e);
        }
    }

    @Override
    public void delete(int id){
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(DELETE_ORDER)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting user with ID: " + id, e);
            throw new RuntimeException("Database error while deleting user", e);
        }
    }

    @Override
    public void deleteOrdersByUserId(int userId) {
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(DELETE_ORDERS_BY_USER_ID)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting order with user ID: " + userId, e);
            throw new RuntimeException("Database error while deleting order", e);
        }
    }

}
