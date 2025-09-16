package com.solvd.online_shop.services.mybatisimpl;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.solvd.online_shop.dao.mybatisimpl.CategoryMyBatisDao;
import com.solvd.online_shop.dao.mybatisimpl.ProductMyBatisDao;
import com.solvd.online_shop.models.Category;
import com.solvd.online_shop.services.interfaces.ICategoryService;

public class CategoryService implements ICategoryService {

    private final CategoryMyBatisDao categoryDao;
    private final ProductMyBatisDao productDao;

    public CategoryService() {
        SqlSessionFactory sqlSessionFactory = buildSessionFactory();
        this.categoryDao = new CategoryMyBatisDao(sqlSessionFactory);
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
    public void add(Category category)  {
        categoryDao.add(category);
    }

    @Override
    public Category getById(int id)  {
        return categoryDao.getById(id);
    }

    @Override
    public List<Category> getAll()  {
        return categoryDao.getAll();
    }

    @Override
    public void update(Category category)  {
        categoryDao.update(category);
    }

    @Override
    public void delete(int id)  {
        categoryDao.delete(id);
    }
}
