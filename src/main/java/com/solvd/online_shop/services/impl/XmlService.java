package com.solvd.online_shop.services.impl;

import com.solvd.online_shop.models.SuppliersWrapper;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import java.io.File;

public class XmlService {

    public SuppliersWrapper parseSuppliersXml(String xmlPath, String xsdPath) throws Exception {
        JAXBContext context = JAXBContext.newInstance(SuppliersWrapper.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        // Validation
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(new File(xsdPath));
        unmarshaller.setSchema(schema);

        File xmlFile = new File(xmlPath);
        return (SuppliersWrapper) unmarshaller.unmarshal(xmlFile);
    }
}
