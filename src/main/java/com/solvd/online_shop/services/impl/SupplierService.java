package com.solvd.online_shop.services.impl;

import java.util.List;

import com.solvd.online_shop.models.Supplier;
import com.solvd.online_shop.utils.dom.SupplierDomParser;

public class SupplierService {

    private final SupplierDomParser parser = new SupplierDomParser();

    public List<Supplier> getSuppliers() {
        return parser.parseSuppliers("src/main/resources/xml/suppliers+products_suppliers.xml");
    }

    public Supplier getSupplierById(int id) {
        return getSuppliers().stream()
                .filter(s -> s.getSupplierId() == id)
                .findFirst()
                .orElse(null);
    }
}
