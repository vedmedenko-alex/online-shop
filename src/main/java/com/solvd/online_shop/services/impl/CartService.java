package com.solvd.online_shop.services.impl;

import java.util.List;

import com.solvd.online_shop.models.Cart;
import com.solvd.online_shop.models.CartItem;
import com.solvd.online_shop.utils.jackson.CartJsonParser;

public class CartService {

    private final CartJsonParser parser = new CartJsonParser();
    private static final String FILE_PATH = "src/main/resources/json/carts-items.json";

    public List<Cart> getAllCarts() {
        return parser.parseCarts(FILE_PATH);
    }

    public List<CartItem> getAllCartItems() {
        return parser.parseCartItems(FILE_PATH);
    }

    public Cart getCartById(int id) {
        return getAllCarts().stream()
                .filter(c -> c.getCartId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<CartItem> getItemsByCartId(int cartId) {
        return getAllCartItems().stream()
                .filter(item -> item.getCartId() == cartId)
                .toList();
    }
}
