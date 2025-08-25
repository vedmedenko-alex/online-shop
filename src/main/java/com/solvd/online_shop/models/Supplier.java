package com.solvd.online_shop.models;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Supplier {
    private int id;
    private String name;
    private String contactInfo;

    public Supplier() {
    }

    public Supplier(int supplierId, String name, String contactInfo) {
        this.id = supplierId;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public int getSupplierId() {
        return id;
    }

    public void setSupplierId(int supplierId) {
        this.id = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("supplierId", id)
                .append("name", name)
                .append("contactInfo", contactInfo)
                .toString();
    }
}
