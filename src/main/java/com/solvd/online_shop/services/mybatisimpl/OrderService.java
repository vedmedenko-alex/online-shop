package com.solvd.online_shop.services.mybatisimpl;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.solvd.online_shop.dao.mybatisimpl.OrderMyBatisDao;
import com.solvd.online_shop.dao.mybatisimpl.ProductMyBatisDao;
import com.solvd.online_shop.models.Order;
import com.solvd.online_shop.services.interfaces.IOrderService;

public class OrderService implements IOrderService {

    private final OrderMyBatisDao orderDao;
    private final ProductMyBatisDao productDao;

    public OrderService() {
        SqlSessionFactory sqlSessionFactory = buildSessionFactory();
        this.orderDao = new OrderMyBatisDao(sqlSessionFactory);
        this.productDao = new ProductMyBatisDao(sqlSessionFactory);
    }

    private SqlSessionFactory buildSessionFactory() {
        try (Reader reader = Resources.getResourceAsReader("mybatis-config.xml")) {
            return new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            throw new RuntimeException("Error building SqlSessionFactory", e);
        }
    }

    @Override
    public void add(Order order)  {
        orderDao.add(order);
    }

    @Override
    public Order getById(int id)  {
        return orderDao.getById(id);
    }

    @Override
    public List<Order> getAll()  {
        return orderDao.getAll();
    }

    @Override
    public void update(Order order)  {
        orderDao.update(order);
    }

    @Override
    public void delete(int id)  {
        orderDao.delete(id);
    }
}
