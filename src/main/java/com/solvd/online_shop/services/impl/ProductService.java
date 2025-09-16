package com.solvd.online_shop.services.impl;

import java.sql.SQLException;
import java.util.List;

import com.solvd.online_shop.dao.impl.CartItemDao;
import com.solvd.online_shop.dao.impl.DiscountDao;
import com.solvd.online_shop.dao.impl.OrderItemDao;
import com.solvd.online_shop.dao.impl.ProductDao;
import com.solvd.online_shop.models.Product;
import com.solvd.online_shop.services.interfaces.IProductService;

public class ProductService  implements IProductService {
    private final ProductDao productDao;
    private final OrderItemDao orderItemDao;
    private final CartItemDao cartItemDao;
    private final DiscountDao discountDao;

    public ProductService() {
        this.productDao = new ProductDao();
        this.orderItemDao = new OrderItemDao();
        this.cartItemDao = new CartItemDao();
        this.discountDao = new DiscountDao();
    }
    @Override
    public void add(Product product)   {
        productDao.add(product);
    }
    @Override
    public Product getById(int id)   {
        return productDao.getById(id);
    }
    @Override
    public List<Product> getAll()   {
        return productDao.getAll();
    }
    @Override
    public void update(Product product)   {
        productDao.update(product);
    }
    @Override
    public void delete(int id)   {
        orderItemDao.deleteOrderItemsByProductId(id);
        cartItemDao.deleteCartItemsByProductId(id);
        discountDao.deleteDiscountsByProductId(id);
        productDao.delete(id);
    }
}
