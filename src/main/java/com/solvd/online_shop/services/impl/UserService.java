package com.solvd.online_shop.services.impl;

import java.sql.SQLException;
import java.util.List;

import com.solvd.online_shop.dao.impl.CartItemDao;
import com.solvd.online_shop.dao.impl.OrderDao;
import com.solvd.online_shop.dao.impl.ReviewDao;
import com.solvd.online_shop.dao.impl.UserDao;
import com.solvd.online_shop.models.User;

public class UserService {
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

    public void addUser(User user) throws SQLException {
        if (userDao.getUserByEmail(user.getEmail()) != null) {
            throw new SQLException("User with email " + user.getEmail() + " already exists.");
        }
        userDao.add(user);
    }

    public User getUserById(int id) throws SQLException {
        return userDao.getById(id);
    }

    public List<User> getAllUsers() throws SQLException {
        return userDao.getAll();
    }

    public void updateUser(User user) throws SQLException {
        userDao.update(user);
    }

    public void deleteUser(int id) throws SQLException {
        orderDao.deleteOrdersByUserId(id);
        cartItemDao.deleteCartItemsByUserId(id);
        reviewDao.deleteReviewsByUserId(id);
        userDao.delete(id);
    }
}
