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
import com.solvd.online_shop.models.Product;
import com.solvd.online_shop.services.interfaces.IOrderItemService;

public class OrderItemService implements IOrderItemService {

    private final OrderMyBatisDao orderDao;
    private final ProductMyBatisDao productDao;

    public OrderItemService() {
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
    public void addOrderItem(Order order, Product product) throws SQLException {
        orderDao.add(order);
        productDao.add(product);
    }

    public List<Product> getAll() throws SQLException {
        return productDao.getAll();
    }
}
