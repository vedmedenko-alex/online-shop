package com.solvd.online_shop.services.mybatisimpl;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

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
     private static final Logger logger = Logger.getLogger(UserService.class.getName());

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
    public void add(User user) {
        try {
            if (userDao.getUserByEmail(user.getEmail()) == null) {
                userDao.add(user);
            } else {
                logger.info("User with email " + user.getEmail() + " already exists. Skipping insert.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while adding user: " + user.getEmail(), e);
        }
    }



    @Override
    public User getById(int id)  {
        return userDao.getById(id);
    }

    @Override
    public List<User> getAll()  {
        return userDao.getAll();
    }

    @Override
    public void update(User user)  {
        userDao.update(user);
    }

    @Override
    public void delete(int id)  {
        orderDao.deleteOrdersByUserId(id);
        cartItemDao.deleteCartItemsByUserId(id);
        reviewDao.deleteReviewsByUserId(id);
        userDao.delete(id);
    }
}
