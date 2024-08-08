package com.mycompany.app.screens;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.w3c.dom.NodeList;

import com.mycompany.app.helpers.XmlParse;

public class BaseScreen {
    protected static final Logger LOG = LogManager.getLogger();
    protected XmlParse pageSource;

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
    public final void click(final WebElement element) {
        LOG.debug("Clicking " + element.getText() + " element");
        element.click();
    }
}
