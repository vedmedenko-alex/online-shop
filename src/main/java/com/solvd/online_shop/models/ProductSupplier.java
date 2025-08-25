package com.solvd.online_shop.models;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ProductSupplier {
    private int productId;
    private int supplierId;

    public ProductSupplier() {
    }

    public ProductSupplier(int productId, int supplierId) {
        this.productId = productId;
        this.supplierId = supplierId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("productId", productId)
                .append("supplierId", supplierId)
                .toString();
    }
}
