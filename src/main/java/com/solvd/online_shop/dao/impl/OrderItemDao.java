package com.solvd.online_shop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.solvd.online_shop.connection.ConnectionPool;

public class OrderItemDao {
    private static final Logger logger = Logger.getLogger(OrderDao.class.getName());

    private static final String DELETE_ORDER_ITEMS_BY_ORDER_ID = "DELETE FROM OrderItems WHERE order_id = ?";
    private static final String DELETE_ORDER_ITEMS_BY_PRODUCT_ID = "DELETE FROM OrderItems WHERE product_id = ?";
    private final ConnectionPool pool = ConnectionPool.getInstance();

    public void deleteOrderItemsByOrderId(int orderId)  {
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(DELETE_ORDER_ITEMS_BY_ORDER_ID)) {
            stmt.setInt(1, orderId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting order item with order ID: " + orderId, e);
            throw new RuntimeException("Database error while deleting order item ", e);
        }
    }

    public void deleteOrderItemsByProductId(int productId) {
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(DELETE_ORDER_ITEMS_BY_PRODUCT_ID)) {
            stmt.setInt(1, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting order item  with product ID: " + productId, e);
            throw new RuntimeException("Database error while deleting order item ", e);
        }
    }
}
