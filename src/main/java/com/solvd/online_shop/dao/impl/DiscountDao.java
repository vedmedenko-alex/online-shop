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
import com.solvd.online_shop.dao.interfaces.IDiscountDao;
import com.solvd.online_shop.models.Discount;

public class DiscountDao implements IDiscountDao {
    private static final Logger logger = Logger.getLogger(DiscountDao.class.getName());

    private static final String INSERT_DISCOUNT = "INSERT INTO Discounts (product_id, percentage, valid_from, valid_to) VALUES (?, ?, ?, ?)";
    private static final String GET_DISCOUNT_BY_ID = "SELECT * FROM Discounts WHERE discount_id = ?";
    private static final String GET_ALL_DISCOUNTS = "SELECT * FROM Discounts";
    private static final String UPDATE_DISCOUNT = "UPDATE Discounts SET product_id = ?, percentage = ?, valid_from = ?, valid_to = ? WHERE discount_id = ?";
    private static final String DELETE_DISCOUNT = "DELETE FROM Discounts WHERE discount_id = ?";
    private static final String DELETE_DISCOUNTS_BY_PRODUCT_ID = "DELETE FROM Discounts WHERE product_id = ?";
    private final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public void add(Discount discount)  {
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(INSERT_DISCOUNT)) {
            stmt.setInt(1, discount.getProductId());
            stmt.setDouble(2, discount.getPercentage());
            stmt.setDate(3, discount.getValidFrom());
            stmt.setDate(4, discount.getValidTo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error adding discount: " + discount, e);
            throw new RuntimeException("Database error while adding discount", e);
        }
    }

    @Override
    public Discount getById(int id)  {
        Discount discount = null;
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(GET_DISCOUNT_BY_ID)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    discount = new Discount(
                            rs.getInt("discount_id"),
                            rs.getInt("product_id"),
                            rs.getDouble("percentage"),
                            rs.getDate("valid_from"),
                            rs.getDate("valid_to"));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching discount by ID: " + id, e);
            throw new RuntimeException("Database error while fetching discount by ID", e);
        }
        return discount;
    }

    @Override
    public List<Discount> getAll()  {
        List<Discount> discounts = new ArrayList<>();
        try (Connection conn = pool.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(GET_ALL_DISCOUNTS)) {
            while (rs.next()) {
                discounts.add(new Discount(
                        rs.getInt("discount_id"),
                        rs.getInt("product_id"),
                        rs.getDouble("percentage"),
                        rs.getDate("valid_from"),
                        rs.getDate("valid_to")));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching all discounts", e);
            throw new RuntimeException("Database error while fetching all discounts", e);
        }
        return discounts;
    }

    @Override
    public void update(Discount discount)  {
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(UPDATE_DISCOUNT)) {
            stmt.setInt(1, discount.getProductId());
            stmt.setDouble(2, discount.getPercentage());
            stmt.setDate(3, discount.getValidFrom());
            stmt.setDate(4, discount.getValidTo());
            stmt.setInt(5, discount.getDiscountId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating discount: " + discount, e);
            throw new RuntimeException("Database error while updating discount", e);
        }
    }

    @Override
    public void delete(int id)  {
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(DELETE_DISCOUNT)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting discount with ID: " + id, e);
            throw new RuntimeException("Database error while deleting discount", e);
        }
    }

    @Override
    public void deleteDiscountsByProductId(int productId)  {
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(DELETE_DISCOUNTS_BY_PRODUCT_ID)) {
            stmt.setInt(1, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting product with product ID: " + productId, e);
            throw new RuntimeException("Database error while deleting discount", e);
        }
    }
}
