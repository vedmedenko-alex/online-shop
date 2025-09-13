package com.solvd.online_shop.dao.mybatisimpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.solvd.online_shop.dao.interfaces.IOrderItemDao;
import com.solvd.online_shop.models.OrderItem;

public class OrderItemMyBatisDao implements IOrderItemDao {

    private final SqlSessionFactory sqlSessionFactory;

    public OrderItemMyBatisDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void add(OrderItem orderItem) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.insert("com.solvd.online_shop.mybatisimpl.mappers.OrderItemMapper.add", orderItem);
        }
    }

    @Override
    public OrderItem getById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectOne("com.solvd.online_shop.mybatisimpl.mappers.OrderItemMapper.getById", id);
        }
    }

    @Override
    public List<OrderItem> getAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("com.solvd.online_shop.mybatisimpl.mappers.OrderItemMapper.getAll");
        }
    }

    @Override
    public void update(OrderItem orderItem) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.update("com.solvd.online_shop.mybatisimpl.mappers.OrderItemMapper.update", orderItem);
        }
    }

    @Override
    public void delete(int id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("com.solvd.online_shop.mybatisimpl.mappers.OrderItemMapper.delete", id);
        }
    }

    @Override
    public void deleteOrderItemsByOrderId(int orderId) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("com.solvd.online_shop.mybatisimpl.mappers.OrderItemMapper.deleteOrderItemsByOrderId", orderId);
        }
    }

    @Override
    public void deleteOrderItemsByProductId(int productId) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("com.solvd.online_shop.mybatisimpl.mappers.OrderItemMapper.deleteOrderItemsByProductId", productId);
        }
    }
}
