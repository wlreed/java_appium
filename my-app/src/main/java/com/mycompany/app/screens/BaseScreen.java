package com.mycompany.app.screens;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
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
    private static final int TIMEOUT = 2000;

    public BaseScreen() {
        getPageSource();
        driverType = sortClassName(APM.getDriver().getClass().toString());
        currScreen = sortClassName(this.getClass().toString());
        LOG.info("Initiating " + currScreen + " with " + driverType);
    }

    public final void getPageSource() {
        try {
            Thread.sleep(TIMEOUT);
        } catch (Exception e) {
            LOG.warn("Can't sleep!");
        }
        xmlPageSource = APM.getDriver().getPageSource();
        pageSource = new XmlParse(xmlPageSource);
    }

    public final String sortClassName(final String className) {
        String[] names = className.split("\\.");
        String newClassName = names[names.length - 1];
        return newClassName;
    }

    public final boolean validateScreen(
        final String identifier, final String expression) {
        NodeList nodes = pageSource.xPath(
                expression);
        for (int i = 0; i < nodes.getLength(); i++) {
            if (nodes.item(i).getNodeValue().equals(identifier)) {
                LOG.info("Screen Validated");
                return true;
            }
        }
        LOG.info("Screen not Validated");
        return false;
    }

    public final boolean identifyXpathElement(
        final String identifier, final String tag) {
        NodeList nodes = findXpathElements(tag);
        for (int i = 0; i < nodes.getLength(); i++) {
            if (nodes.item(i).getNodeValue().equals(identifier)) {
                LOG.info("Screen Validated");
                return true;
            }
        }
        return false;
    }

    public final NodeList findXpathElements(final String tag) {
        NodeList nodes = pageSource.xPath(tag);
        return nodes;
    }

    public final void click(final WebElement element) {
        LOG.debug("Clicking " + element.getText() + " element");
        try {
            Thread.sleep(TIMEOUT);
        } catch (Exception e) {
            LOG.warn("Could not sleep!");
        }
        element.click();
    }

    public final void sendkeys(final WebElement element, final String string) {
        element.sendKeys(string);
    }

    public final void dismissKeyboard() {
        if (driverType.equals("AndroidDriver")) {
            APM.getDriver().navigate().back();
        } else if (driverType.equals("IOSDriver")) {
            click(APM.getDriver().findElement(By.name("Return")));
        }
    }

    public final boolean isAlert() {
        if (driverType.equals("IOSDriver")) {
            NodeList nodes = pageSource.xPath("//XCUIElementTypeAlert/@type");
            if (nodes.getLength() == 1) {
                return true;
            }
        } else if (driverType.equals("AndroidDriver")) {
            NodeList nodes = pageSource.xPath("//android.widget.ScrollView");
            LOG.info("nodes length: " + nodes.getLength());
            if (nodes.getLength() == 2) {
                return true;
            }
        }
        return false;
    }
}
