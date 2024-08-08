package com.mycompany.app.helpers;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XmlParse {
    private static final Logger LOG = LogManager.getLogger();
    private Document xmlDocument;
    private DocumentBuilderFactory factory;
    private DocumentBuilder builder;
    private XPathFactory xpathfactory;
    private XPath xpath;
    private XPathExpression expr;

    public XmlParse(final String xmlString) throws Exception {
        factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xmlString));
        xmlDocument = builder.parse(is);
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
