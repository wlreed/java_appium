package com.mycompany.app.screens;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.PageFactory;

import com.mycompany.app.drivers.APM;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class HomeScreen extends BaseScreen {
    public Map<String, String> elementMap = new HashMap<>();

    public HomeScreen() {
        super();
        if (driverType.equals("IOSDriver")) {
            elementMap.put("identifier", "TheApp");
            elementMap.put("idExpression", "//XCUIElementTypeStaticText/@name");
        } else if (driverType.equals("AndroidDriver")) {
            elementMap.put("identifier", "TheApp");
            elementMap.put("idExpression", "//android.widget.TextView/@text");
        }
        validateScreen(elementMap.get(
            "identifier"), elementMap.get("idExpression"));
        PageFactory.initElements(
            new AppiumFieldDecorator(APM.getDriver()), this);
    }

    @AndroidFindBy(accessibility = "Echo Box")
    @iOSXCUITFindBy(accessibility = "Echo Box")
    public WebElement echoBoxElement;

    @AndroidFindBy(accessibility = "Login Screen")
    @iOSXCUITFindBy(accessibility = "Login Screen")
    public WebElement logInElement;

    @AndroidFindBy(accessibility = "Clipboard Demo")
    @iOSXCUITFindBy(accessibility = "Clipboard Demo")
    public WebElement clipboardDemoElement;

    @AndroidFindBy(accessibility = "Webview Demo")
    @iOSXCUITFindBy(accessibility = "Webview Demo")
    public WebElement webviewDemoElement;

    @AndroidFindBy(accessibility = "Dual Webview Demo")
    @iOSXCUITFindBy(accessibility = "Dual Webview Demo")
    public WebElement dualWebviewDemoElement;

    @AndroidFindBy(accessibility = "List Demo")
    @iOSXCUITFindBy(accessibility = "List Demo")
    public WebElement listDemoElement;

    @AndroidFindBy(accessibility = "Photo Demo")
    @iOSXCUITFindBy(accessibility = "Photo Demo")
    public WebElement photoDemoElement;

    @AndroidFindBy(accessibility = "Geolocation Demo")
    @iOSXCUITFindBy(accessibility = "Geolocation Demo")
    public WebElement geoDemoElement;

    @AndroidFindBy(accessibility = "Picker Demo")
    @iOSXCUITFindBy(accessibility = "Picker Demo")
    public WebElement pickerDemoElement;

    @AndroidFindBy(accessibility = "Verify Phone Number")
    public WebElement verifyPhoneNumberElement;
}
