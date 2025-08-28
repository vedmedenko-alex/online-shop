package com.solvd.online_shop.models;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"id", "name", "contactInfo", "products"})
@XmlRootElement(name = "supplier")
public class Supplier {

    private int id;
    private String name;

    @XmlElement(name = "contact_info")
    private String contactInfo;

    @XmlElementWrapper(name = "products")
    @XmlElement(name = "product")
    private List<Product> products;

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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
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
