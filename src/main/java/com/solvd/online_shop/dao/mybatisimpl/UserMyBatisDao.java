package com.solvd.online_shop.dao.mybatisimpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.solvd.online_shop.dao.interfaces.IUserDao;
import com.solvd.online_shop.models.User;

public class UserMyBatisDao implements IUserDao {

    private final SqlSessionFactory sqlSessionFactory;

    public UserMyBatisDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void add(User user) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.insert("com.solvd.online_shop.mybatisimpl.mappers.UserMapper.add", user);
        }
    }

    @Override
    public User getById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectOne("com.solvd.online_shop.mybatisimpl.mappers.UserMapper.getById", id);
        }
    }

    @Override
    public List<User> getAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("com.solvd.online_shop.mybatisimpl.mappers.UserMapper.getAll");
        }
    }

    @Override
    public void update(User user) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.update("com.solvd.online_shop.mybatisimpl.mappers.UserMapper.update", user);
        }
    }

    @Override
    public void delete(int id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("com.solvd.online_shop.mybatisimpl.mappers.UserMapper.delete", id);
        }
    }

    @Override
    public User getUserByEmail(String email) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            return session.selectOne("com.solvd.online_shop.mybatisimpl.mappers.UserMapper.getUserByEmail", email);
        }
    }
}
