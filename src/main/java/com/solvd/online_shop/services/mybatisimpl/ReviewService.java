package com.solvd.online_shop.services.mybatisimpl;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.solvd.online_shop.dao.mybatisimpl.ReviewMyBatisDao;
import com.solvd.online_shop.models.Review;
import com.solvd.online_shop.services.interfaces.IReviewService;

public class ReviewService implements IReviewService {

    private final ReviewMyBatisDao reviewDao;

    public ReviewService() {
        SqlSessionFactory sqlSessionFactory = buildSessionFactory();
        this.reviewDao = new ReviewMyBatisDao(sqlSessionFactory);
    }

    private SqlSessionFactory buildSessionFactory() {
        try (Reader reader = Resources.getResourceAsReader("mybatis-config.xml")) {
            return new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            throw new RuntimeException("Error building SqlSessionFactory", e);
        }
    }

    @Override
    public void add(Review review) throws SQLException {
        reviewDao.add(review);
    }

    @Override
    public Review getById(int id) throws SQLException {
        return reviewDao.getById(id);
    }

    @Override
    public List<Review> getAll() throws SQLException {
        return reviewDao.getAll();
    }

    @Override
    public void update(Review review) throws SQLException {
        reviewDao.update(review);
    }

    @Override
    public void delete(int id) throws SQLException {
        reviewDao.delete(id);
    }
}
