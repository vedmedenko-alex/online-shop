package com.solvd.online_shop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.solvd.online_shop.connection.ConnectionPool;
import com.solvd.online_shop.dao.interfaces.IProductDao;
import com.solvd.online_shop.models.Product;

public class ProductDao implements IProductDao {

    private static final String INSERT_PRODUCT = "INSERT INTO Products (category_id, name, description, price, stock_quantity) VALUES (?, ?, ?, ?, ?)";
    private static final String GET_PRODUCT_BY_ID = "SELECT * FROM Products WHERE product_id = ?";
    private static final String GET_ALL_PRODUCTS = "SELECT * FROM Products";
    private static final String UPDATE_PRODUCT = "UPDATE Products SET category_id = ?, name = ?, description = ?, price = ?, stock_quantity = ? WHERE product_id = ?";
    private static final String DELETE_PRODUCT = "DELETE FROM Products WHERE product_id = ?";
    ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public void add(Product product) throws SQLException {
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(INSERT_PRODUCT)) {
            stmt.setInt(1, product.getCategoryId());
            stmt.setString(2, product.getName());
            stmt.setString(3, product.getDescription());
            stmt.setDouble(4, product.getPrice());
            stmt.setInt(5, product.getStockQuantity());
            stmt.executeUpdate();
        }
    }

    @Override
    public Product getById(int id) throws SQLException {
        Product product = null;
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(GET_PRODUCT_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                product = new Product(rs.getInt("product_id"), rs.getInt("category_id"), rs.getString("name"),
                        rs.getString("description"), rs.getDouble("price"), rs.getInt("stock_quantity"));
            }
        }
        return product;
    }

    @Override
    public List<Product> getAll() throws SQLException {
        List<Product> products = new ArrayList<>();
        try (Connection conn = pool.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(GET_ALL_PRODUCTS)) {
            while (rs.next()) {
                products.add(new Product(rs.getInt("product_id"), rs.getInt("category_id"), rs.getString("name"),
                        rs.getString("description"), rs.getDouble("price"), rs.getInt("stock_quantity")));
            }
        }
        return products;
    }

    @Override
    public void update(Product product) throws SQLException {
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(UPDATE_PRODUCT)) {
            stmt.setInt(1, product.getCategoryId());
            stmt.setString(2, product.getName());
            stmt.setString(3, product.getDescription());
            stmt.setDouble(4, product.getPrice());
            stmt.setInt(5, product.getStockQuantity());
            stmt.setInt(6, product.getProductId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(DELETE_PRODUCT)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
