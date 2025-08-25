package com.solvd.online_shop.services.impl;

import java.sql.SQLException;
import java.util.List;

import com.solvd.online_shop.dao.impl.DiscountDao;
import com.solvd.online_shop.models.Discount;

public class DiscountService {
    private final DiscountDao discountDao;

    public DiscountService() {
        this.discountDao = new DiscountDao();
    }

    public void addDiscount(Discount discount) throws SQLException {
        discountDao.add(discount);
    }

    public List<Discount> getAllDiscounts() throws SQLException {
        return discountDao.getAll();
    }

    public void updateDiscount(Discount discount) throws SQLException {
        discountDao.update(discount);
    }

    public void deleteDiscount(int id) throws SQLException {
        discountDao.delete(id);
    }
}
