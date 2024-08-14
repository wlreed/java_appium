package com.mycompany.app.screens;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.mycompany.app.drivers.APM;

import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class SecretAreaScreen extends BaseScreen {
    public Map<String, String> elementMap = new HashMap<>();

    public SecretAreaScreen() {
        super();
        elementMap.put("alice", "You are logged in as alice");
        PageFactory.initElements(
            new AppiumFieldDecorator(APM.getDriver()), this);
    }

    @AndroidFindBy(accessibility = "Secret Area")
    @iOSXCUITFindBy(accessibility = "Secret Area")
    public WebElement screenId;

    @AndroidFindBy(accessibility = "Logout")
    @iOSXCUITFindBy(accessibility = "Logout")
    public WebElement logoutButton;

    @AndroidFindBy(accessibility = "You are logged in as alice")
    @iOSXCUITFindBy(accessibility = "You are logged in as alice")
    public WebElement confirmAliceLogin;

    @AndroidFindBy(accessibility = "Back")
    @iOSXCUITFindBy(accessibility = "Back")
    public WebElement backButton;
}
