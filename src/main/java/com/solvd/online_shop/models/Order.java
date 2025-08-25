package com.solvd.online_shop.models;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Order {
    private int orderId;
    private int userId;
    private String orderDate;
    private String status;
    private double totalAmount;

    public Order() {
    }

    public Order(int orderId, int userId, String orderDate, String status, double totalAmount) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderDate = orderDate;
        this.status = status;
        this.totalAmount = totalAmount;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("orderId", orderId)
                .append("userId", userId)
                .append("orderDate", orderDate)
                .append("status", status)
                .append("totalAmount", totalAmount)
                .toString();
    }
}
