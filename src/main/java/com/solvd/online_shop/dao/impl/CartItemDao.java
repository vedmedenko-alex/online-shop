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
import com.solvd.online_shop.dao.interfaces.ICartItemDao;
import com.solvd.online_shop.models.CartItem;

public class CartItemDao implements ICartItemDao {
    private static final Logger logger = Logger.getLogger(CartItemDao.class.getName());

    private static final String INSERT_CART_ITEM = "INSERT INTO CartItems (cart_id, product_id, quantity) VALUES (?, ?, ?)";
    private static final String GET_CART_ITEM_BY_ID = "SELECT * FROM CartItems WHERE cart_item_id = ?";
    private static final String GET_ALL_CART_ITEMS = "SELECT * FROM CartItems";
    private static final String UPDATE_CART_ITEM = "UPDATE CartItems SET cart_id = ?, product_id = ?, quantity = ? WHERE cart_item_id = ?";
    private static final String DELETE_CART_ITEM = "DELETE FROM CartItems WHERE cart_item_id = ?";
    private static final String DELETE_CART_ITEMS_BY_CART_ID = "DELETE FROM CartItems WHERE cart_id = ?";
    private static final String DELETE_CART_ITEMS_BY_PRODUCT_ID = "DELETE FROM CartItems WHERE product_id = ?";
    private static final String DELETE_CART_ITEMS_BY_USER_ID = "DELETE FROM CartItems WHERE cart_id IN (SELECT cart_id FROM Carts WHERE user_id = ?)";
    private static final String GET_CART_ITEMS_BY_USER_ID = "SELECT * FROM CartItems WHERE cart_id = (SELECT cart_id FROM Carts WHERE user_id = ?)";

    private final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public void add(CartItem cartItem)  {
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(INSERT_CART_ITEM)) {
            stmt.setInt(1, cartItem.getCartId());
            stmt.setInt(2, cartItem.getProductId());
            stmt.setInt(3, cartItem.getQuantity());
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error adding cart item: " + cartItem, e);
            throw new RuntimeException("Database error while adding cart item", e);
        }
    }

    @Override
    public CartItem getById(int id)  {
        CartItem cartItem = null;
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(GET_CART_ITEM_BY_ID)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cartItem = new CartItem(
                            rs.getInt("cart_item_id"),
                            rs.getInt("cart_id"),
                            rs.getInt("product_id"),
                            rs.getInt("quantity"));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching cart item by ID: " + id, e);
            throw new RuntimeException("Database error while fetching cart item by ID", e);
        }
        return cartItem;
    }

    @Override
    public List<CartItem> getAll()  {
        List<CartItem> cartItems = new ArrayList<>();
        try (Connection conn = pool.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(GET_ALL_CART_ITEMS)) {
            while (rs.next()) {
                cartItems.add(new CartItem(
                        rs.getInt("cart_item_id"),
                        rs.getInt("cart_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity")));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching all cart items", e);
            throw new RuntimeException("Database error while fetching all cart items", e);
        }
        return cartItems;
    }

    @Override
    public void update(CartItem cartItem)  {
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(UPDATE_CART_ITEM)) {
            stmt.setInt(1, cartItem.getCartId());
            stmt.setInt(2, cartItem.getProductId());
            stmt.setInt(3, cartItem.getQuantity());
            stmt.setInt(4, cartItem.getCartItemId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating cart item: " + cartItem, e);
            throw new RuntimeException("Database error while updating cart item", e);
        }
    }

    @Override
    public void delete(int id)  {
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(DELETE_CART_ITEM)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting cart item with ID: " + id, e);
            throw new RuntimeException("Database error while deleting cart item", e);
        }
    }

    @Override
    public void deleteCartItemsByCartId(int cartId)  {
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(DELETE_CART_ITEMS_BY_CART_ID)) {
            stmt.setInt(1, cartId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting cart item with cart ID: " + cartId, e);
            throw new RuntimeException("Database error while deleting cart item", e);
        }
    }

    @Override
    public void deleteCartItemsByProductId(int productId)  {
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(DELETE_CART_ITEMS_BY_PRODUCT_ID)) {
            stmt.setInt(1, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting cart item with product ID: " + productId, e);
            throw new RuntimeException("Database error while deleting cart item", e);
        }
    }

    @Override
    public void deleteCartItemsByUserId(int userId)  {
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(DELETE_CART_ITEMS_BY_USER_ID)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting cart item with user ID: " + userId, e);
            throw new RuntimeException("Database error while deleting cart item", e);
        }
    }

    @Override
    public List<CartItem> getCartItemsByUserId(int userId)  {
        List<CartItem> cartItems = new ArrayList<>();
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(GET_CART_ITEMS_BY_USER_ID)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    cartItems.add(new CartItem(
                            rs.getInt("cart_item_id"),
                            rs.getInt("cart_id"),
                            rs.getInt("product_id"),
                            rs.getInt("quantity")));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching cart item with user ID: " + userId, e);
            throw new RuntimeException("Database error while fetching cart item", e);
        }
        return cartItems;
    }
}
