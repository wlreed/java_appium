package com.mycompany.app.screens;

import org.openqa.selenium.Alert;

import com.mycompany.app.drivers.APM;

public class Alerts extends BaseScreen {
    public Alert alertDriver;
    public Alerts() {
        super();
        alertDriver = APM.getDriver().switchTo().alert();
    }

    public void acceptAlert() {
        alertDriver.accept();
    }

    public void dismissAlert() {
        alertDriver.dismiss();
    }
}
