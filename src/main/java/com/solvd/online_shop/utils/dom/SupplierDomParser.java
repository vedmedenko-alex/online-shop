package com.solvd.online_shop.utils.dom;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.solvd.online_shop.models.ProductSupplier;
import com.solvd.online_shop.models.Supplier;

public class SupplierDomParser {

    private static final Logger logger = LogManager.getLogger(SupplierDomParser.class);

    public List<Supplier> parseSuppliers(String filePath) {
        List<Supplier> suppliers = new ArrayList<>();

        try {
            File inputFile = new File(filePath);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList supplierNodes = doc.getElementsByTagName("supplier");

            for (int i = 0; i < supplierNodes.getLength(); i++) {
                Node node = supplierNodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element supplierElem = (Element) node;

                    int supplierId = Integer.parseInt(supplierElem.getAttribute("id"));
                    String name = supplierElem.getElementsByTagName("name").item(0).getTextContent();
                    String contactInfo = supplierElem.getElementsByTagName("contact_info").item(0).getTextContent();

                    Supplier supplier = new Supplier(supplierId, name, contactInfo);

                    List<ProductSupplier> productSuppliers = new ArrayList<>();
                    NodeList productNodes = supplierElem.getElementsByTagName("product");
                    for (int j = 0; j < productNodes.getLength(); j++) {
                        Element productElem = (Element) productNodes.item(j);
                        int productId = Integer.parseInt(productElem.getAttribute("id"));
                        productSuppliers.add(new ProductSupplier(productId, supplierId));
                    }

                    logger.info("Parsed supplier: " + supplier);
                    productSuppliers.forEach(p -> logger.info("  with product: " + p));
                    suppliers.add(supplier);
                }
            }
        } catch (Exception e) {
            logger.warn("Error parsing suppliers: " + e.getMessage());
        }

        return suppliers;
    }
}
