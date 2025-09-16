package com.solvd.online_shop.services.impl;

import java.sql.SQLException;
import java.util.List;

import com.solvd.online_shop.dao.impl.ReviewDao;
import com.solvd.online_shop.models.Review;
import com.solvd.online_shop.services.interfaces.IReviewService;

public class ReviewService implements IReviewService{
    private ReviewDao reviewDao;

    public ReviewService() {
        reviewDao = new ReviewDao();
    }
    @Override
    public void add(Review review)   {
        reviewDao.add(review);
    }
    @Override
    public Review getById(int id)   {
        return reviewDao.getById(id);
    }
    @Override
    public List<Review> getAll()   {
        return reviewDao.getAll();
    }
    @Override
    public void update(Review review)   {
        reviewDao.update(review);
    }
    @Override
    public void delete(int id)   {
        reviewDao.delete(id);
    }
}
