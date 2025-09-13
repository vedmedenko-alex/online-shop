package com.solvd.online_shop.services.mybatisimpl;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.solvd.online_shop.dao.mybatisimpl.CartItemMyBatisDao;
import com.solvd.online_shop.dao.mybatisimpl.OrderMyBatisDao;
import com.solvd.online_shop.dao.mybatisimpl.ReviewMyBatisDao;
import com.solvd.online_shop.dao.mybatisimpl.UserMyBatisDao;
import com.solvd.online_shop.models.User;
import com.solvd.online_shop.services.interfaces.IUserService;

public class UserService implements IUserService {

    private final UserMyBatisDao userDao;
    private final OrderMyBatisDao orderDao;
    private final CartItemMyBatisDao cartItemDao;
    private final ReviewMyBatisDao reviewDao;

    public UserService() {
        SqlSessionFactory sqlSessionFactory = buildSessionFactory();
        this.userDao = new UserMyBatisDao(sqlSessionFactory);
        this.orderDao = new OrderMyBatisDao(sqlSessionFactory);
        this.cartItemDao = new CartItemMyBatisDao(sqlSessionFactory);
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
    public void add(User user) throws SQLException {
        if (userDao.getUserByEmail(user.getEmail()) != null) {
            throw new SQLException("User with email " + user.getEmail() + " already exists.");
        }
        userDao.add(user);
    }

    @Override
    public User getById(int id) throws SQLException {
        return userDao.getById(id);
    }

    @Override
    public List<User> getAll() throws SQLException {
        return userDao.getAll();
    }

    @Override
    public void update(User user) throws SQLException {
        userDao.update(user);
    }

    @Override
    public void delete(int id) throws SQLException {
        orderDao.deleteOrdersByUserId(id);
        cartItemDao.deleteCartItemsByUserId(id);
        reviewDao.deleteReviewsByUserId(id);
        userDao.delete(id);
    }
}
