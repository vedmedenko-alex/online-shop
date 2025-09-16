package com.solvd.online_shop.factories;

import com.solvd.online_shop.dao.impl.*;
import com.solvd.online_shop.services.impl.*;
import com.solvd.online_shop.services.interfaces.*;
import com.solvd.online_shop.services.mybatisimpl.*;

public class ServiceFactory {

    public enum Implementation {
        JDBC,
        MYBATIS
    }

    private final Implementation impl;

    public ServiceFactory(Implementation impl) {
        this.impl = impl;
    }

    public IUserService createUserService() {
        switch (impl) {
            case JDBC:
                return new com.solvd.online_shop.services.impl.UserService();
            case MYBATIS:
                return new com.solvd.online_shop.services.mybatisimpl.UserService();
            default:
                throw new IllegalArgumentException("Unknown implementation: " + impl);
        }
    }

    public IReviewService createReviewService() {
        switch (impl) {
            case JDBC:
                return new com.solvd.online_shop.services.impl.ReviewService();
            case MYBATIS:
                return new com.solvd.online_shop.services.mybatisimpl.ReviewService();
            default:
                throw new IllegalArgumentException("Unknown implementation: " + impl);
        }
    }

    public IProductService createProductService() {
        switch (impl) {
            case JDBC:
                return new com.solvd.online_shop.services.impl.ProductService();
            case MYBATIS:
                return new com.solvd.online_shop.services.mybatisimpl.ProductService();
            default:
                throw new IllegalArgumentException("Unknown implementation: " + impl);
        }
    }

    public IOrderService createOrderService() {
        switch (impl) {
            case JDBC:
                return new com.solvd.online_shop.services.impl.OrderService();
            case MYBATIS:
                return new com.solvd.online_shop.services.mybatisimpl.OrderService();
            default:
                throw new IllegalArgumentException("Unknown implementation: " + impl);
        }
    }

    public IOrderItemService createOrderItemService() {
        switch (impl) {
            case JDBC:
                return new com.solvd.online_shop.services.impl.OrderItemService();
            case MYBATIS:
                return new com.solvd.online_shop.services.mybatisimpl.OrderItemService();
            default:
                throw new IllegalArgumentException("Unknown implementation: " + impl);
        }
    }

    
    public IDiscountService createDiscountService() {
        switch (impl) {
            case JDBC:
                return new com.solvd.online_shop.services.impl.DiscountService();
            case MYBATIS:
                return new com.solvd.online_shop.services.mybatisimpl.DiscountService();
            default:
                throw new IllegalArgumentException("Unknown implementation: " + impl);
        }
    }

    
    public ICategoryService createCategoryService() {
        switch (impl) {
            case JDBC:
                return new com.solvd.online_shop.services.impl.CategoryService();
            case MYBATIS:
                return new com.solvd.online_shop.services.mybatisimpl.CategoryService();
            default:
                throw new IllegalArgumentException("Unknown implementation: " + impl);
        }
    }

}

