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
import com.solvd.online_shop.dao.interfaces.IUserDao;
import com.solvd.online_shop.models.User;

public class UserDao implements IUserDao {

    private static final Logger logger = Logger.getLogger(UserDao.class.getName());

    private static final String INSERT_USER = "INSERT INTO Users (name, email, password) VALUES (?, ?, ?)";
    private static final String GET_USER_BY_ID = "SELECT * FROM Users WHERE user_id = ?";
    private static final String GET_ALL_USERS = "SELECT * FROM Users";
    private static final String UPDATE_USER = "UPDATE Users SET name = ?, email = ?, password = ? WHERE user_id = ?";
    private static final String DELETE_USER = "DELETE FROM Users WHERE user_id = ?";
    private static final String GET_USER_BY_EMAIL = "SELECT * FROM Users WHERE email = ?";

    private final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public User getUserByEmail(String email) {
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(GET_USER_BY_EMAIL)) {

            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("user_id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("password")
                    );
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching user by email: " + email, e);
            throw new RuntimeException("Database error while fetching user by email", e);
        }
        return null;
    }

    @Override
    public void add(User user) {
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(INSERT_USER)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error adding user: " + user, e);
            throw new RuntimeException("Database error while adding user", e);
        }
    }

    @Override
    public User getById(int id) {
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(GET_USER_BY_ID)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("user_id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("password")
                    );
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching user by ID: " + id, e);
            throw new RuntimeException("Database error while fetching user by ID", e);
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Connection conn = pool.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(GET_ALL_USERS)) {

            while (rs.next()) {
                users.add(new User(
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password")
                ));
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching all users", e);
            throw new RuntimeException("Database error while fetching all users", e);
        }
        return users;
    }

    @Override
    public void update(User user) {
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(UPDATE_USER)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setInt(4, user.getUserId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating user: " + user, e);
            throw new RuntimeException("Database error while updating user", e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(DELETE_USER)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting user with ID: " + id, e);
            throw new RuntimeException("Database error while deleting user", e);
        }
    }
}
