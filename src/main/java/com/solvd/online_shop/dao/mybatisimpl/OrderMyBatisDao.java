package com.solvd.online_shop.dao.mybatisimpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.solvd.online_shop.dao.interfaces.IOrderDao;
import com.solvd.online_shop.models.Order;

public class OrderMyBatisDao implements IOrderDao {

    private final SqlSessionFactory sqlSessionFactory;

    public OrderMyBatisDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void add(Order order) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.insert("com.solvd.online_shop.mybatisimpl.mappers.OrderMapper.add", order);
        }
    }

    @Override
    public Order getById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectOne("com.solvd.online_shop.mybatisimpl.mappers.OrderMapper.getById", id);
        }
    }

    @Override
    public List<Order> getAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("com.solvd.online_shop.mybatisimpl.mappers.OrderMapper.getAll");
        }
    }

    @Override
    public void update(Order order) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.update("com.solvd.online_shop.mybatisimpl.mappers.OrderMapper.update", order);
        }
    }

    @Override
    public void delete(int id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("com.solvd.online_shop.mybatisimpl.mappers.OrderMapper.delete", id);
        }
    }

    @Override
    public void deleteOrdersByUserId(int userId) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("com.solvd.online_shop.mybatisimpl.mappers.OrderMapper.deleteOrdersByUserId", userId);
        }
    }
}
