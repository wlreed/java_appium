package com.mycompany.app.screens;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.mycompany.app.drivers.APM;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Alerts extends BaseScreen {
    public Alert alertDriver;
    public Alerts() {
        super();
        if (driverType.equals("IOSDriver")) {
            alertDriver = APM.getDriver().switchTo().alert();
        }
        PageFactory.initElements(
            new AppiumFieldDecorator(APM.getDriver()), this);
    }

    public final void acceptAlert() {
        if (driverType.equals("IOSDriver")) {
            alertDriver.accept();
        } else if (driverType.equals(("AndroidDriver"))) {
            click(okButton);
        }
    }

    public final void dismissAlert() {
        if (driverType.equals("IOSDriver")) {
            alertDriver.dismiss();
        }
    }
    @AndroidFindBy(id = "android:id/button1")
    public WebElement okButton;

    @AndroidFindBy(id = "android:id/message")
    public WebElement alertId;

}
