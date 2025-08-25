package com.solvd.online_shop.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.solvd.online_shop.models.CartItem;

public interface ICartItemDao extends IGenericDao<CartItem> {
    // void addCartItem(CartItem cartItem) throws SQLException;

    // CartItem getCartItemById(int id) throws SQLException;

    // List<CartItem> getAllCartItems() throws SQLException;

    // void updateCartItem(CartItem cartItem) throws SQLException;

    // void deleteCartItem(int id) throws SQLException;

    void deleteCartItemsByCartId(int cartId) throws SQLException;

    void deleteCartItemsByProductId(int productId) throws SQLException;

    List<CartItem> getCartItemsByUserId(int userId) throws SQLException;

    void deleteCartItemsByUserId(int userId) throws SQLException;
}
