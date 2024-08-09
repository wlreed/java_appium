package com.mycompany.app.helpers;

import java.io.StringReader;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlParse {
    private static final Logger LOG = LogManager.getLogger();
    private Document xmlDocument;
    private DocumentBuilderFactory factory;
    private DocumentBuilder builder;
    private XPathFactory xpathfactory;
    private XPath xpath;
    private XPathExpression expr;

    public XmlParse(final String xmlString) {
        factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOG.warn("Parsing config problem");
            e.printStackTrace();
            return;
        }
        InputSource is = new InputSource(new StringReader(xmlString));     
        try {
            xmlDocument = builder.parse(is);
        } catch (SAXException | IOException e) {
            LOG.warn("Parsing xmlDocument problem");
            e.printStackTrace();
            return;
        }
    }

    public final NodeList xPath(final String expression) throws Exception {
        xpathfactory = XPathFactory.newInstance();
        xpath = xpathfactory.newXPath();
        expr = xpath.compile(expression);
        Object result = expr.evaluate(xmlDocument, XPathConstants.NODESET);
        NodeList nodes = (NodeList) result;
        return nodes;
    }
}
