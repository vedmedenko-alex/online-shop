package com.solvd.online_shop.models;

import java.sql.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Discount {
    private int discountId;
    private int productId;
    private double percentage;
    private Date validFrom;
    private Date validTo;

    public Discount(int discountId, int productId, double percentage, Date validFrom, Date validTo) {
        this.discountId = discountId;
        this.productId = productId;
        this.percentage = percentage;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("discountId", discountId)
                .append("productId", productId)
                .append("percentage", percentage)
                .append("validFrom", validFrom)
                .append("validTo", validTo)
                .toString();
    }
}
