package com.solvd.online_shop.utils.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.online_shop.models.Cart;
import com.solvd.online_shop.models.CartItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.ArrayList;

import java.util.List;

public class CartJsonParser {

    private static final Logger logger = LogManager.getLogger(CartJsonParser.class);

    public List<Cart> parseCarts(String filePath) {
        List<Cart> carts = new ArrayList<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(new File(filePath));

            for (JsonNode node : root.get("carts")) {
                Cart cart = mapper.treeToValue(node, Cart.class);
                carts.add(cart);
            }
        } catch (Exception e) {
            logger.warn("Error parsing carts JSON: " + e.getMessage());
        }
        return carts;
    }

    public List<CartItem> parseCartItems(String filePath) {
        List<CartItem> cartItems = new ArrayList<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(new File(filePath));

            for (JsonNode node : root.get("cartItems")) {
                CartItem cartItem = mapper.treeToValue(node, CartItem.class);
                cartItems.add(cartItem);
            }
        } catch (Exception e) {
            logger.warn("Error parsing cartItems JSON: " + e.getMessage());
        }
        return cartItems;
    }
}
