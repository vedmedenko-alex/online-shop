package com.solvd.online_shop.dao.mybatisimpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.solvd.online_shop.dao.interfaces.IReviewDao;
import com.solvd.online_shop.models.Review;

public class ReviewMyBatisDao implements IReviewDao {

    private final SqlSessionFactory sqlSessionFactory;

    public ReviewMyBatisDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void add(Review review) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.insert("com.solvd.online_shop.mybatisimpl.mappers.ReviewMapper.add", review);
        }
    }

    @Override
    public Review getById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectOne("com.solvd.online_shop.mybatisimpl.mappers.ReviewMapper.getById", id);
        }
    }

    @Override
    public List<Review> getAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("com.solvd.online_shop.mybatisimpl.mappers.ReviewMapper.getAll");
        }
    }

    @Override
    public void update(Review review) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.update("com.solvd.online_shop.mybatisimpl.mappers.ReviewMapper.update", review);
        }
    }

    @Override
    public void delete(int id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("com.solvd.online_shop.mybatisimpl.mappers.ReviewMapper.delete", id);
        }
    }

    @Override
    public void deleteReviewsByUserId(int userId) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("com.solvd.online_shop.mybatisimpl.mappers.ReviewMapper.deleteReviewsByUserId", userId);
        }
    }
}
