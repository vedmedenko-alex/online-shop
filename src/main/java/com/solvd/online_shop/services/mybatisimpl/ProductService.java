package com.solvd.online_shop.services.mybatisimpl;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.solvd.online_shop.dao.mybatisimpl.CartItemMyBatisDao;
import com.solvd.online_shop.dao.mybatisimpl.DiscountMyBatisDao;
import com.solvd.online_shop.dao.mybatisimpl.OrderItemMyBatisDao;
import com.solvd.online_shop.dao.mybatisimpl.ProductMyBatisDao;
import com.solvd.online_shop.models.Product;
import com.solvd.online_shop.services.interfaces.IProductService;

public class ProductService implements IProductService {

    private final ProductMyBatisDao productDao;
    private final OrderItemMyBatisDao orderItemDao;
    private final CartItemMyBatisDao cartItemDao;
    private final DiscountMyBatisDao discountDao;

    public ProductService() {
        SqlSessionFactory sqlSessionFactory = buildSessionFactory();
        this.productDao = new ProductMyBatisDao(sqlSessionFactory);
        this.orderItemDao = new OrderItemMyBatisDao(sqlSessionFactory);
        this.cartItemDao = new CartItemMyBatisDao(sqlSessionFactory);
        this.discountDao = new DiscountMyBatisDao(sqlSessionFactory);
    }

    private SqlSessionFactory buildSessionFactory() {
        try (Reader reader = Resources.getResourceAsReader("mybatis-config.xml")) {
            return new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            throw new RuntimeException("Error building SqlSessionFactory", e);
        }
    }

    @Override
    public void add(Product product)  {
        productDao.add(product);
    }

    @Override
    public Product getById(int id)  {
        return productDao.getById(id);
    }

    @Override
    public List<Product> getAll()  {
        return productDao.getAll();
    }

    @Override
    public void update(Product product)  {
        productDao.update(product);
    }

    @Override
    public void delete(int id)  {
        orderItemDao.deleteOrderItemsByProductId(id);
        cartItemDao.deleteCartItemsByProductId(id);
        discountDao.deleteDiscountsByProductId(id);
        productDao.delete(id);
    }
}
