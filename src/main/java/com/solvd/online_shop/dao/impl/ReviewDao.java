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
import com.solvd.online_shop.dao.interfaces.IReviewDao;
import com.solvd.online_shop.models.Review;

public class ReviewDao implements IReviewDao {

    private static final Logger logger = Logger.getLogger(ReviewDao.class.getName());

    private static final String INSERT_REVIEW = "INSERT INTO Reviews (product_id, user_id, rating, comment, created_at) VALUES (?, ?, ?, ?, ?)";
    private static final String GET_REVIEW_BY_ID = "SELECT * FROM Reviews WHERE review_id = ?";
    private static final String GET_ALL_REVIEWS = "SELECT * FROM Reviews";
    private static final String UPDATE_REVIEW = "UPDATE Reviews SET product_id = ?, user_id = ?, rating = ?, comment = ?, created_at = ? WHERE review_id = ?";
    private static final String DELETE_REVIEW = "DELETE FROM Reviews WHERE review_id = ?";
    private static final String DELETE_REVIEWS_BY_USER_ID = "DELETE FROM Reviews WHERE user_id = ?";
    private final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public void add(Review review)  {
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(INSERT_REVIEW)) {
            stmt.setInt(1, review.getProductId());
            stmt.setInt(2, review.getUserId());
            stmt.setInt(3, review.getRating());
            stmt.setString(4, review.getComment());
            stmt.setTimestamp(5, review.getCreatedAt());
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error adding review: " + review, e);
            throw new RuntimeException("Database error while adding review", e);
        }
    }

    @Override
    public Review getById(int reviewId)  {
        Review review = null;

        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(GET_REVIEW_BY_ID)) {
            stmt.setInt(1, reviewId);
            try (ResultSet rs = stmt.executeQuery()) {

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
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching review by ID: " + reviewId, e);
            throw new RuntimeException("Database error while fetching review by ID", e);
        }

        return review;
    }

    @Override
    public List<Review> getAll()  {
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
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching all reviews", e);
            throw new RuntimeException("Database error while fetching all reviews", e);
        }
        return reviews;
    }

    @Override
    public void update(Review review)  {
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(UPDATE_REVIEW)) {
            stmt.setInt(1, review.getProductId());
            stmt.setInt(2, review.getUserId());
            stmt.setInt(3, review.getRating());
            stmt.setString(4, review.getComment());
            stmt.setTimestamp(5, review.getCreatedAt());
            stmt.setInt(6, review.getReviewId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating review: " + review, e);
            throw new RuntimeException("Database error while updating review", e);
        }
    }

    @Override
    public void delete(int id)  {
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(DELETE_REVIEW)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting review with ID: " + id, e);
            throw new RuntimeException("Database error while deleting review", e);
        }
    }

    @Override
    public void deleteReviewsByUserId(int userId)  {
        try (Connection conn = pool.getConnection(); PreparedStatement stmt = conn.prepareStatement(DELETE_REVIEWS_BY_USER_ID)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting review with user ID: " + userId, e);
            throw new RuntimeException("Database error while deleting review", e);
        }
    }
}
