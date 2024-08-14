package com.mycompany.app.drivers;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mycompany.app.helpers.AutoProps;

import static com.mycompany.app.server.APMServer.startServer;
import static com.mycompany.app.server.APMServer.stopServer;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public final class APM {
    private static APM uniqueInstance;
    private static final ThreadLocal<AppiumDriver> DRIVER = new ThreadLocal<>();
    private static final Logger LOG = LogManager.getLogger();
    private static AutoProps settings;
    private static String appPath = "";
    private static final int MAX_DRIVER_TIMEOUT = 15;

    private APM() {
        settings = AutoProps.getInstance();
        appPath = String.valueOf(Path.of(System.getProperty(
            "user.dir") + settings.configs.getProperty("app")));
        startServer(settings.configs.getProperty("platformName"));
        LOG.info("Attaching client to server");
        if (settings.configs.getProperty("platformName").equals("ios")) {
            try {
                setDriver(new IOSDriver(new URL(
                    "http://127.0.0.1:4723/wd/hub"), xcuiTestOptions()));
            } catch (final MalformedURLException e) {
                throw new RuntimeException(e);
            }
        } else if (settings.configs.getProperty(
            "platformName").equals("android")) {
            try {
                setDriver(new AndroidDriver(new URL(
                    "http://127.0.0.1:4723/wd/hub"),
                uiAutomator2OptionsWdio()));
            } catch (final MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
        setupDriverTimeouts();
    }

    public static APM getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new APM();
        }
        return uniqueInstance;
    }

     /**
     * getDriver
     * Getter for the iOS Driver
     * @return
     */
    public static AppiumDriver getDriver() {
        return APM.DRIVER.get();
    }

    /**
     * setDriver
     * Setter for the iOS Driver
     * @param driver
     */
    private static void setDriver(final AppiumDriver driver) {
        APM.DRIVER.set(driver);
    }

    /**
     * xcuiTestOptions
     * Contains all the settings for iOS Driver (Client)
     * @return
     */
    private static XCUITestOptions xcuiTestOptions() {
        return new XCUITestOptions()
                .setDeviceName(settings.configs.getProperty("deviceName"))
                .setAutomationName(
                    settings.configs.getProperty("automationName"))
                .setNewCommandTimeout(Duration.ofSeconds(
                    settings.getSettingAsInteger("newCommandTimeout")))
                .setPlatformVersion(
                    settings.configs.getProperty("platformVersion"))
                .setMaxTypingFrequency(10)
                .setApp(appPath);
    }

    /**
     * uiAutomator2OptionsWdio
     * Holds all the settings for Android Driver (Client)
     * @return
     */
    private static UiAutomator2Options uiAutomator2OptionsWdio() {
        final UiAutomator2Options uiAutomator2Options;
        uiAutomator2Options = new UiAutomator2Options().setAvd(
            settings.configs.getProperty("avd"))
            .setAvdLaunchTimeout(Duration.ofSeconds(
                settings.getSettingAsInteger("avdLaunchTimeout")))
            .setAvdReadyTimeout(Duration.ofSeconds(
                settings.getSettingAsInteger("avdReadyTimeout")))
            .setDeviceName(settings.configs.getProperty("deviceName"))
            .setAutomationName(
                settings.configs.getProperty("automationName"))
            .setApp(appPath);
        return uiAutomator2Options;
    }

    /**
     * quitSession
     * Quits the iOS Driver (Session) as well as the server
     */
    public static void quitSession() {
        if (null != DRIVER.get()) {
            LOG.info("Closing the driver...");
            getDriver().quit();
            DRIVER.remove();
            stopServer();
            // resets driver (Singleton)
            uniqueInstance = null;
        }
    }

     /**
     * setupDriverTimeouts
     * Sets up iOS Driver related timeouts
     */
    private static void setupDriverTimeouts() {
        getDriver().manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(MAX_DRIVER_TIMEOUT));
    }
}
