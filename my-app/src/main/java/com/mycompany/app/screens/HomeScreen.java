package com.mycompany.app.screens;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.PageFactory;

import com.mycompany.app.drivers.APM;
import com.mycompany.app.helpers.XmlParse;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class HomeScreen extends BaseScreen {
    private String xmlPageSource = "";
    public Map<String, String> elementMap = new HashMap<>();

    public HomeScreen() throws Exception {
        LOG.info("Initiating HomeScreen");
        elementMap.put("identifier", "TheApp");
        xmlPageSource = APM.getDriver().getPageSource();
        pageSource = new XmlParse(xmlPageSource);
        //validateScreen(elementMap.get("identifier"));
        PageFactory.initElements(
            new AppiumFieldDecorator(APM.getDriver()), this);
    }
    @AndroidFindBy(accessibility = "Login Screen")
    @iOSXCUITFindBy(accessibility = "Login Screen")
    public WebElement logInElement;

    @AndroidFindBy(accessibility = "Echo Box")
    @iOSXCUITFindBy(accessibility = "Echo Box")
    public WebElement echoBoxElement;
}
