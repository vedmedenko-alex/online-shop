package com.solvd.online_shop.dao.mybatisimpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.solvd.online_shop.dao.interfaces.IDiscountDao;
import com.solvd.online_shop.models.Discount;

public class DiscountMyBatisDao implements IDiscountDao {

    private final SqlSessionFactory sqlSessionFactory;

    public DiscountMyBatisDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void add(Discount discount) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.insert("com.solvd.online_shop.mybatisimpl.mappers.DiscountMapper.add", discount);
        }
    }

    @Override
    public Discount getById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectOne("com.solvd.online_shop.mybatisimpl.mappers.DiscountMapper.getById", id);
        }
    }

    @Override
    public List<Discount> getAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("com.solvd.online_shop.mybatisimpl.mappers.DiscountMapper.getAll");
        }
    }

    @Override
    public void update(Discount discount) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.update("com.solvd.online_shop.mybatisimpl.mappers.DiscountMapper.update", discount);
        }
    }

    @Override
    public void delete(int id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("com.solvd.online_shop.mybatisimpl.mappers.DiscountMapper.delete", id);
        }
    }

    @Override
    public void deleteDiscountsByProductId(int productId) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("com.solvd.online_shop.mybatisimpl.mappers.DiscountMapper.deleteDiscountsByProductId", productId);
        }
    }
}
