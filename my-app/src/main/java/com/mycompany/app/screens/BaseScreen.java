package com.mycompany.app.screens;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.w3c.dom.NodeList;

import com.mycompany.app.drivers.APM;
import com.mycompany.app.helpers.XmlParse;

public class BaseScreen {
    protected static final Logger LOG = LogManager.getLogger();
    private String xmlPageSource = "";
    protected XmlParse pageSource;
    protected String driverType;
    protected String currScreen;

    public BaseScreen() {
        getPageSource();
        driverType = sortClassName(APM.getDriver().getClass().toString());
        currScreen = sortClassName(this.getClass().toString());
        LOG.info("Initiating " + currScreen + " with " + driverType);
    }

    public final void getPageSource() {
        xmlPageSource = APM.getDriver().getPageSource();
        pageSource = new XmlParse(xmlPageSource);
    }

    public final String sortClassName(String className) {
        String[] names = className.split("\\.");
        className = names[names.length - 1];
        return className;
    }

    public final boolean validateScreen(final String identifier) {
        NodeList nodes = pageSource.xPath(
                "//XCUIElementTypeStaticText/@name");
        for (int i = 0; i < nodes.getLength(); i++) {
            if (nodes.item(i).getNodeValue().equals(identifier)) {
                LOG.info("Screen Validated");
                return true;
            }
        }
        LOG.info("Screen not Validated");
        return false;
    }

    public final boolean identifyXpathElement(String identifier, String tag) {
        NodeList nodes = findXpathElements(tag);
        for (int i = 0; i < nodes.getLength(); i++) {
            if (nodes.item(i).getNodeValue().equals(identifier)) {
                LOG.info("Screen Validated");
                return true;
            }
        }
        return false;
    }

    public final NodeList findXpathElements(String tag) {
        NodeList nodes = pageSource.xPath(tag);
        return nodes;
    }

    public final void click(final WebElement element) {
        LOG.debug("Clicking " + element.getText() + " element");
        element.click();
    }

    public final void sendkeys(final WebElement element, final String string) {
        element.sendKeys(string);
    }

    public final boolean isAlert() {
        NodeList nodes = pageSource.xPath("//XCUIElementTypeAlert/@type");
        if (nodes.getLength() == 1) {
            return true;
        }
        return false;
    }
}
