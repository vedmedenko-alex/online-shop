package com.solvd.online_shop.dao.interfaces;

import java.sql.SQLException;

import com.solvd.online_shop.models.Review;

public interface IReviewDao extends IGenericDao<Review>{
    // void addReview(Review review) throws SQLException;

    // Review getReviewById(int id) throws SQLException;

    // List<Review> getAllReviews() throws SQLException;

    // void updateReview(Review review) throws SQLException;

    // void deleteReview(int id) throws SQLException;

    void deleteReviewsByUserId(int userId) throws SQLException;
}
