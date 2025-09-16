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
import com.solvd.online_shop.dao.interfaces.ICategoryDao;
import com.solvd.online_shop.models.Category;

public class CategoryDao implements ICategoryDao {
    private static final Logger logger = Logger.getLogger(CategoryDao.class.getName());

    private static final String INSERT_CATEGORY = "INSERT INTO Categories (name, description) VALUES (?, ?)";
    private static final String GET_CATEGORY_BY_ID = "SELECT * FROM Categories WHERE category_id = ?";
    private static final String GET_ALL_CATEGORIES = "SELECT * FROM Categories";
    private static final String UPDATE_CATEGORY = "UPDATE Categories SET name = ?, description = ? WHERE category_id = ?";
    private static final String DELETE_CATEGORY = "DELETE FROM Categories WHERE category_id = ?";

    private final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public void add(Category category)  {
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(INSERT_CATEGORY)) {
            stmt.setString(1, category.getName());
            stmt.setString(2, category.getDescription());
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error category user: " + category, e);
            throw new RuntimeException("Database error while adding category", e);
        }
    }

    @Override
    public Category getById(int id)  {
        Category category = null;
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(GET_CATEGORY_BY_ID)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    category = new Category(rs.getInt("category_id"), rs.getString("name"), rs.getString("description"));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching category by ID: " + id, e);
            throw new RuntimeException("Database error while fetching category by ID", e);
        }
        return category;
    }

    @Override
    public List<Category> getAll()  {
        List<Category> categories = new ArrayList<>();
        try (Connection conn = pool.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(GET_ALL_CATEGORIES)) {
            while (rs.next()) {
                categories
                        .add(new Category(rs.getInt("category_id"), rs.getString("name"), rs.getString("description")));
            }
         } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching all categories", e);
            throw new RuntimeException("Database error while fetching all categories", e);
        }
        return categories;
    }

    @Override
    public void update(Category category)  {
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(UPDATE_CATEGORY)) {
            stmt.setString(1, category.getName());
            stmt.setString(2, category.getDescription());
            stmt.setInt(3, category.getCategoryId());
            stmt.executeUpdate();
         } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating category: " + category, e);
            throw new RuntimeException("Database error while updating category", e);
        }
    }

    @Override
    public void delete(int id)  {
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(DELETE_CATEGORY)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting category with ID: " + id, e);
            throw new RuntimeException("Database error while deleting category", e);
        }
    }
}
