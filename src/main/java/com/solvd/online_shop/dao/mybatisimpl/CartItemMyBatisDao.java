package com.solvd.online_shop.dao.mybatisimpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.solvd.online_shop.dao.interfaces.ICartItemDao;
import com.solvd.online_shop.models.CartItem;

public class CartItemMyBatisDao implements ICartItemDao {

    private final SqlSessionFactory sqlSessionFactory;

    public CartItemMyBatisDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void add(CartItem cartItem) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.insert("com.solvd.online_shop.mybatisimpl.mappers.CartItemMapper.add", cartItem);
        }
    }

    @Override
    public CartItem getById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectOne("com.solvd.online_shop.mybatisimpl.mappers.CartItemMapper.getById", id);
        }
    }

    @Override
    public List<CartItem> getAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("com.solvd.online_shop.mybatisimpl.mappers.CartItemMapper.getAll");
        }
    }

    @Override
    public void update(CartItem cartItem) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.update("com.solvd.online_shop.mybatisimpl.mappers.CartItemMapper.update", cartItem);
        }
    }

    @Override
    public void delete(int id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("com.solvd.online_shop.mybatisimpl.mappers.CartItemMapper.delete", id);
        }
    }

    @Override
    public void deleteCartItemsByProductId(int productId) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("com.solvd.online_shop.mybatisimpl.mappers.CartItemMapper.deleteCartItemsByProductId", productId);
        }
    }
    @Override
    public void deleteCartItemsByCartId(int cartId) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("com.solvd.online_shop.mybatisimpl.mappers.CartItemMapper.deleteCartItemsByCartId", cartId);
        }
    }

    @Override
    public List<CartItem> getCartItemsByUserId(int userId) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            return session.selectList("com.solvd.online_shop.mybatisimpl.mappers.CartItemMapper.getCartItemsByUserId", userId);
        }
    }

    @Override
    public void deleteCartItemsByUserId(int userId) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("com.solvd.online_shop.mybatisimpl.mappers.CartItemMapper.deleteCartItemsByUserId", userId);
        }
    }

}
