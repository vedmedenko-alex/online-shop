package com.solvd.online_shop.services.impl;

import java.sql.SQLException;
import java.util.List;

import com.solvd.online_shop.dao.impl.CartItemDao;
import com.solvd.online_shop.dao.impl.OrderDao;
import com.solvd.online_shop.dao.impl.ReviewDao;
import com.solvd.online_shop.dao.impl.UserDao;
import com.solvd.online_shop.models.User;
import com.solvd.online_shop.services.interfaces.IUserService;

public class UserService implements IUserService {

    private final UserDao userDao;
    private final OrderDao orderDao;
    private final CartItemDao cartItemDao;
    private final ReviewDao reviewDao;

    public UserService() {
        this.userDao = new UserDao();
        this.orderDao = new OrderDao();
        this.cartItemDao = new CartItemDao();
        this.reviewDao = new ReviewDao();
    }

    @Override
    public void add(User user) {
        if (userDao.getUserByEmail(user.getEmail()) != null) {
            throw new RuntimeException("User with email " + user.getEmail() + " already exists.");
        }
        userDao.add(user);
    }

    @Override
    public User getById(int id) {
        return userDao.getById(id);
    }

    @Override
    public List<User> getAll()  {
        return userDao.getAll();
    }

    @Override
    public void update(User user)   {
        userDao.update(user);
    }

    @Override
    public void delete(int id)   {
        orderDao.deleteOrdersByUserId(id);
        cartItemDao.deleteCartItemsByUserId(id);
        reviewDao.deleteReviewsByUserId(id);
        userDao.delete(id);
    }
}
