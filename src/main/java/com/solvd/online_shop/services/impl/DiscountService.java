package com.solvd.online_shop.services.impl;

import java.sql.SQLException;
import java.util.List;

import com.solvd.online_shop.dao.impl.DiscountDao;
import com.solvd.online_shop.models.Category;
import com.solvd.online_shop.models.Discount;
import com.solvd.online_shop.services.interfaces.IDiscountService;

public class DiscountService implements IDiscountService {

    private final DiscountDao discountDao;

    public DiscountService() {
        this.discountDao = new DiscountDao();
    }

    @Override
    public void add(Discount discount)   {
        discountDao.add(discount);
    }

    @Override
    public List<Discount> getAll()   {
        return discountDao.getAll();
    }

    @Override
    public Discount getById(int id)   {
        return discountDao.getById(id);
    }

    @Override
    public void update(Discount discount)   {
        discountDao.update(discount);
    }

    @Override
    public void delete(int id)   {
        discountDao.delete(id);
    }
}
