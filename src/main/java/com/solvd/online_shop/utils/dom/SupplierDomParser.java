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

import com.solvd.online_shop.models.Product;
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

                    int supplierId = Integer.parseInt(
                            supplierElem.getElementsByTagName("id").item(0).getTextContent()
                    );
                    String supplierName = supplierElem.getElementsByTagName("name").item(0).getTextContent();
                    String contactInfo = supplierElem.getElementsByTagName("contact_info").item(0).getTextContent();

                    Supplier supplier = new Supplier(supplierId, supplierName, contactInfo);

                    List<Product> products = new ArrayList<>();
                    NodeList productNodes = supplierElem.getElementsByTagName("product");
                    for (int j = 0; j < productNodes.getLength(); j++) {
                        Element productElem = (Element) productNodes.item(j);

                        int productId = Integer.parseInt(
                                productElem.getElementsByTagName("productId").item(0).getTextContent()
                        );
                        int categoryId = Integer.parseInt(
                                productElem.getElementsByTagName("categoryId").item(0).getTextContent()
                        );
                        String productName = productElem.getElementsByTagName("name").item(0).getTextContent();
                        String description = productElem.getElementsByTagName("description").item(0).getTextContent();
                        double price = Double.parseDouble(
                                productElem.getElementsByTagName("price").item(0).getTextContent()
                        );
                        int stockQuantity = Integer.parseInt(
                                productElem.getElementsByTagName("stockQuantity").item(0).getTextContent()
                        );

                        Product product = new Product(productId, categoryId, productName, description, price, stockQuantity);
                        products.add(product);
                    }

                    supplier.setProducts(products);

                    logger.info("Parsed supplier: " + supplier);
                    products.forEach(p -> logger.info("  with product: " + p));
                    suppliers.add(supplier);
                }
            }
        } catch (Exception e) {
            logger.warn("Error parsing suppliers: " + e.getMessage());
        }

        return suppliers;
    }
}
