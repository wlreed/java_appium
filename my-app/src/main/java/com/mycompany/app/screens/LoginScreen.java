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

public class LoginScreen extends BaseScreen {
    private String xmlPageSource = "";
    public Map<String, String> elementMap = new HashMap<>();
    public String defaultUser = "alice";
    public String defaultPassword = "mypassword";

    public LoginScreen() {
        super();
        elementMap.put("identifier", "Login");
        xmlPageSource = APM.getDriver().getPageSource();
        pageSource = new XmlParse(xmlPageSource);
        //validateScreen(elementMap.get("identifier"));
        PageFactory.initElements(
            new AppiumFieldDecorator(APM.getDriver()), this);
    }

    @AndroidFindBy(accessibility = "username")
    @iOSXCUITFindBy(accessibility = "username")
    public WebElement usernameElement;

    @AndroidFindBy(accessibility = "password")
    @iOSXCUITFindBy(accessibility = "password")
    public WebElement passwordElement;

    @AndroidFindBy(accessibility = "loginBtn")
    @iOSXCUITFindBy(accessibility = "loginBtn")
    public WebElement loginButton;
}
