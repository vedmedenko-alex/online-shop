package com.solvd.online_shop.models;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ProductImage {
    private int imageId;
    private int productId;
    private String imageUrl;

    public ProductImage() {
    }

    public ProductImage(int imageId, int productId, String imageUrl) {
        this.imageId = imageId;
        this.productId = productId;
        this.imageUrl = imageUrl;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("imageId", imageId)
                .append("productId", productId)
                .append("imageUrl", imageUrl)
                .toString();
    }
}
