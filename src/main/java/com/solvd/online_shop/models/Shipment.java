package com.solvd.online_shop.models;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Shipment {
    private int shipmentId;
    private int orderId;
    private String shipmentDate;
    private String deliveryDate;
    private String status;

    public Shipment() {
    }

    public Shipment(int shipmentId, int orderId, String shipmentDate, String deliveryDate, String status) {
        this.shipmentId = shipmentId;
        this.orderId = orderId;
        this.shipmentDate = shipmentDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
    }

    public int getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(int shipmentId) {
        this.shipmentId = shipmentId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(String shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("shipmentId", shipmentId)
                .append("orderId", orderId)
                .append("shipmentDate", shipmentDate)
                .append("deliveryDate", deliveryDate)
                .append("status", status)
                .toString();
    }
}
