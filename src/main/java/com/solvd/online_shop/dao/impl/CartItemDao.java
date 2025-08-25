package com.solvd.online_shop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.solvd.online_shop.connection.ConnectionPool;
import com.solvd.online_shop.dao.interfaces.ICartItemDao;
import com.solvd.online_shop.models.CartItem;

public class CartItemDao implements ICartItemDao {

    private static final String INSERT_CART_ITEM = "INSERT INTO CartItems (cart_id, product_id, quantity) VALUES (?, ?, ?)";
    private static final String GET_CART_ITEM_BY_ID = "SELECT * FROM CartItems WHERE cart_item_id = ?";
    private static final String GET_ALL_CART_ITEMS = "SELECT * FROM CartItems";
    private static final String UPDATE_CART_ITEM = "UPDATE CartItems SET cart_id = ?, product_id = ?, quantity = ? WHERE cart_item_id = ?";
    private static final String DELETE_CART_ITEM = "DELETE FROM CartItems WHERE cart_item_id = ?";
    private static final String DELETE_CART_ITEMS_BY_CART_ID = "DELETE FROM CartItems WHERE cart_id = ?";
    private static final String DELETE_CART_ITEMS_BY_PRODUCT_ID = "DELETE FROM CartItems WHERE product_id = ?";
    private static final String DELETE_CART_ITEMS_BY_USER_ID = "DELETE FROM CartItems WHERE cart_id IN (SELECT cart_id FROM Carts WHERE user_id = ?)";
    private static final String GET_CART_ITEMS_BY_USER_ID = "SELECT * FROM CartItems WHERE cart_id = (SELECT cart_id FROM Carts WHERE user_id = ?)";
    
    ConnectionPool pool = ConnectionPool.getInstance();
    @Override
    public void add(CartItem cartItem) throws SQLException {
        try (Connection conn = pool.getConnection();
                PreparedStatement stmt = conn.prepareStatement(INSERT_CART_ITEM)) {
            stmt.setInt(1, cartItem.getCartId());
            stmt.setInt(2, cartItem.getProductId());
            stmt.setInt(3, cartItem.getQuantity());
            stmt.executeUpdate();
        }
    }

    @Override
    public CartItem getById(int id) throws SQLException {
        CartItem cartItem = null;
        try (Connection conn = pool.getConnection();
                PreparedStatement stmt = conn.prepareStatement(GET_CART_ITEM_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                cartItem = new CartItem(
                        rs.getInt("cart_item_id"),
                        rs.getInt("cart_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"));
            }
        }
        return cartItem;
    }

    @Override
    public List<CartItem> getAll() throws SQLException {
        List<CartItem> cartItems = new ArrayList<>();
        try (Connection conn = pool.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(GET_ALL_CART_ITEMS)) {
            while (rs.next()) {
                cartItems.add(new CartItem(
                        rs.getInt("cart_item_id"),
                        rs.getInt("cart_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity")));
            }
        }
        return cartItems;
    }

    @Override
    public void update(CartItem cartItem) throws SQLException {
        try (Connection conn = pool.getConnection();
                PreparedStatement stmt = conn.prepareStatement(UPDATE_CART_ITEM)) {
            stmt.setInt(1, cartItem.getCartId());
            stmt.setInt(2, cartItem.getProductId());
            stmt.setInt(3, cartItem.getQuantity());
            stmt.setInt(4, cartItem.getCartItemId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        try (Connection conn = pool.getConnection();
                PreparedStatement stmt = conn.prepareStatement(DELETE_CART_ITEM)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteCartItemsByCartId(int cartId) throws SQLException {
        try (Connection conn = pool.getConnection();
                PreparedStatement stmt = conn.prepareStatement(DELETE_CART_ITEMS_BY_CART_ID)) {
            stmt.setInt(1, cartId);
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteCartItemsByProductId(int productId) throws SQLException {
        try (Connection conn = pool.getConnection();
                PreparedStatement stmt = conn.prepareStatement(DELETE_CART_ITEMS_BY_PRODUCT_ID)) {
            stmt.setInt(1, productId);
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteCartItemsByUserId(int userId) throws SQLException {
        try (Connection conn = pool.getConnection();
                PreparedStatement stmt = conn.prepareStatement(DELETE_CART_ITEMS_BY_USER_ID)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<CartItem> getCartItemsByUserId(int userId) throws SQLException {
        List<CartItem> cartItems = new ArrayList<>();
        try (Connection conn = pool.getConnection();
                PreparedStatement stmt = conn.prepareStatement(GET_CART_ITEMS_BY_USER_ID)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                cartItems.add(new CartItem(
                        rs.getInt("cart_item_id"),
                        rs.getInt("cart_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity")));
            }
        }
        return cartItems;
    }
}
