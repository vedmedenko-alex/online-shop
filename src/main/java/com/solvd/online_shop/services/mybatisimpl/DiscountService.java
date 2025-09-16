package com.solvd.online_shop.services.mybatisimpl;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.solvd.online_shop.dao.mybatisimpl.DiscountMyBatisDao;
import com.solvd.online_shop.models.Discount;
import com.solvd.online_shop.services.interfaces.IDiscountService;

public class DiscountService implements IDiscountService {

    private final DiscountMyBatisDao discountDao;

    public DiscountService() {
        SqlSessionFactory sqlSessionFactory = buildSessionFactory();
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
    public void add(Discount discount)  {
        discountDao.add(discount);
    }

    @Override
    public List<Discount> getAll()  {
        return discountDao.getAll();
    }

    @Override
    public Discount getById(int id)  {
        return discountDao.getById(id);
    }

    @Override
    public void update(Discount discount)  {
        discountDao.update(discount);
    }

    @Override
    public void delete(int id)  {
        discountDao.delete(id);
    }
}
