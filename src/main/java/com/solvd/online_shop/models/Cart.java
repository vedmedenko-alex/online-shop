package com.solvd.online_shop.models;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Cart {
    private int cartId;
    private int userId;

    public Cart() {
    }

    public Cart(int cartId, int userId) {
        this.cartId = cartId;
        this.userId = userId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("cartId", cartId)
                .append("userId", userId)
                .toString();
    }
}
