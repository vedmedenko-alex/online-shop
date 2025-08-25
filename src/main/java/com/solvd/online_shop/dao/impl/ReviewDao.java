package com.solvd.online_shop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.solvd.online_shop.connection.ConnectionPool;
import com.solvd.online_shop.dao.interfaces.IReviewDao;
import com.solvd.online_shop.models.Review;

public class ReviewDao implements IReviewDao {

    private static final String INSERT_REVIEW = "INSERT INTO Reviews (product_id, user_id, rating, comment, created_at) VALUES (?, ?, ?, ?, ?)";
    private static final String GET_REVIEW_BY_ID = "SELECT * FROM Reviews WHERE review_id = ?";
    private static final String GET_ALL_REVIEWS = "SELECT * FROM Reviews";
    private static final String UPDATE_REVIEW = "UPDATE Reviews SET product_id = ?, user_id = ?, rating = ?, comment = ?, created_at = ? WHERE review_id = ?";
    private static final String DELETE_REVIEW = "DELETE FROM Reviews WHERE review_id = ?";
    private static final String DELETE_REVIEWS_BY_USER_ID = "DELETE FROM Reviews WHERE user_id = ?";
    ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public void add(Review review) throws SQLException {
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(INSERT_REVIEW)) {
            stmt.setInt(1, review.getProductId());
            stmt.setInt(2, review.getUserId());
            stmt.setInt(3, review.getRating());
            stmt.setString(4, review.getComment());
            stmt.setTimestamp(5, review.getCreatedAt());
            stmt.executeUpdate();
        }
    }

    @Override
    public Review getById(int reviewId) throws SQLException {
        Review review = null;

        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(GET_REVIEW_BY_ID)) {
            stmt.setInt(1, reviewId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                review = new Review(
                        rs.getInt("review_id"),
                        rs.getInt("product_id"),
                        rs.getInt("user_id"),
                        rs.getInt("rating"),
                        rs.getString("comment"),
                        rs.getTimestamp("created_at"));
            }
        }

        return review;
    }

    @Override
    public List<Review> getAll() throws SQLException {
        List<Review> reviews = new ArrayList<>();
        try (Connection conn = pool.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(GET_ALL_REVIEWS)) {
            while (rs.next()) {
                reviews.add(new Review(
                        rs.getInt("review_id"),
                        rs.getInt("product_id"),
                        rs.getInt("user_id"),
                        rs.getInt("rating"),
                        rs.getString("comment"),
                        rs.getTimestamp("created_at")));
            }
        }
        return reviews;
    }

    @Override
    public void update(Review review) throws SQLException {
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(UPDATE_REVIEW)) {
            stmt.setInt(1, review.getProductId());
            stmt.setInt(2, review.getUserId());
            stmt.setInt(3, review.getRating());
            stmt.setString(4, review.getComment());
            stmt.setTimestamp(5, review.getCreatedAt());
            stmt.setInt(6, review.getReviewId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(DELETE_REVIEW)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteReviewsByUserId(int userId) throws SQLException {
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(DELETE_REVIEWS_BY_USER_ID)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        }
    }
}
