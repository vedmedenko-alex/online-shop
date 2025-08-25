package com.solvd.online_shop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.solvd.online_shop.connection.ConnectionPool;
import com.solvd.online_shop.dao.interfaces.IDiscountDao;
import com.solvd.online_shop.models.Discount;

public class DiscountDao implements IDiscountDao {

    private static final String INSERT_DISCOUNT = "INSERT INTO Discounts (product_id, percentage, valid_from, valid_to) VALUES (?, ?, ?, ?)";
    private static final String GET_DISCOUNT_BY_ID = "SELECT * FROM Discounts WHERE discount_id = ?";
    private static final String GET_ALL_DISCOUNTS = "SELECT * FROM Discounts";
    private static final String UPDATE_DISCOUNT = "UPDATE Discounts SET product_id = ?, percentage = ?, valid_from = ?, valid_to = ? WHERE discount_id = ?";
    private static final String DELETE_DISCOUNT = "DELETE FROM Discounts WHERE discount_id = ?";
    private static final String DELETE_DISCOUNTS_BY_PRODUCT_ID = "DELETE FROM Discounts WHERE product_id = ?";
    ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public void add(Discount discount) throws SQLException {
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(INSERT_DISCOUNT)) {
            stmt.setInt(1, discount.getProductId());
            stmt.setDouble(2, discount.getPercentage());
            stmt.setDate(3, discount.getValidFrom());
            stmt.setDate(4, discount.getValidTo());
            stmt.executeUpdate();
        }
    }

    @Override
    public Discount getById(int id) throws SQLException {
        Discount discount = null;
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(GET_DISCOUNT_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                discount = new Discount(
                        rs.getInt("discount_id"),
                        rs.getInt("product_id"),
                        rs.getDouble("percentage"),
                        rs.getDate("valid_from"),
                        rs.getDate("valid_to"));
            }
        }
        return discount;
    }

    @Override
    public List<Discount> getAll() throws SQLException {
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
        }
        return discounts;
    }

    @Override
    public void update(Discount discount) throws SQLException {
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(UPDATE_DISCOUNT)) {
            stmt.setInt(1, discount.getProductId());
            stmt.setDouble(2, discount.getPercentage());
            stmt.setDate(3, discount.getValidFrom());
            stmt.setDate(4, discount.getValidTo());
            stmt.setInt(5, discount.getDiscountId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(DELETE_DISCOUNT)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteDiscountsByProductId(int productId) throws SQLException {
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(DELETE_DISCOUNTS_BY_PRODUCT_ID)) {
            stmt.setInt(1, productId);
            stmt.executeUpdate();
        }
    }
}
