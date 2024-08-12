package com.mycompany.app.screens;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.NodeList;

import com.mycompany.app.drivers.APM;
import com.mycompany.app.helpers.XmlParse;

import io.appium.java_client.AppiumDriver;

public class BaseScreen {
    protected static final Logger LOG = LogManager.getLogger();
    protected XmlParse pageSource;
    protected String driverType;
    protected String currScreen;

    public BaseScreen() {
        driverType = sortClassName(APM.getDriver().getClass().toString());
        currScreen = sortClassName(this.getClass().toString());
        LOG.info("Initiating " + currScreen + " with " + driverType);
    }

    public final String sortClassName(String className) {
        String[] names = className.split("\\.");
        className = names[names.length - 1];
        return className;
    }

    public final boolean validateScreen(
            final String identifier) throws Exception {
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

    public final boolean findXpathElement(String identifier, String tag) {
        NodeList nodes = pageSource.xPath(tag);
        for (int i = 0; i < nodes.getLength(); i++) {
            if (nodes.item(i).getNodeValue().equals(identifier)) {
                LOG.info("Screen Validated");
                return true;
            }
        }
        return false;
    }

    public final void click(final WebElement element) {
        LOG.debug("Clicking " + element.getText() + " element");
        element.click();
    }

    public final void sendkeys(final WebElement element, final String string) {
        element.sendKeys(string);
    }
}
