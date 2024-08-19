package com.mycompany.app.screens;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.mycompany.app.drivers.APM;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class LoginScreen extends BaseScreen {
    public Map<String, String> elementMap = new HashMap<>();
    public String defaultUser = "alice";
    public String defaultPassword = "mypassword";

    public LoginScreen() {
        super();
        if (driverType.equals("IOSDriver")) {
            elementMap.put("identifier", "Login");
            elementMap.put("idExpression", "//XCUIElementTypeStaticText/@name");
        } else if (driverType.equals("AndroidDriver")) {
            elementMap.put("identifier", "Login");
            elementMap.put("idExpression", "//android.widget.TextView/@text");
        }
        validateScreen(elementMap.get(
            "identifier"), elementMap.get("idExpression"));
        PageFactory.initElements(
            new AppiumFieldDecorator(APM.getDriver()), this);
    }

    @AndroidFindBy(accessibility =  "Navigate Up")
    @iOSXCUITFindBy(iOSNsPredicate =
    "name == \"TheApp\" AND label == \"TheApp\" "
    + "AND type == \"XCUIElementTypeButton\"")
    public WebElement backButton;

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
