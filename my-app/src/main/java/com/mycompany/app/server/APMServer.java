package com.mycompany.app.server;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.BASEPATH;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.SESSION_OVERRIDE;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.LOG_LEVEL;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.USE_DRIVERS;


import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * AppiumServerManager
 * Handles starting the Appium Server for all test platforms
 */
public class APMServer {
    private static final Logger LOG = LogManager.getLogger();

    public static AppiumDriverLocalService service;

    public static AppiumDriverLocalService getService() {
        return service;
    }

    /**
     * startServer
     * Starts the server according to the platform given
     * @param platformName
     */
    public static void startServer(final String platformName) {
        final AppiumServiceBuilder builder = new AppiumServiceBuilder();

        if (platformName.equalsIgnoreCase("android")) {
            builder.withIPAddress("127.0.0.1")
                .withArgument(BASEPATH, "/wd/hub")
                .withArgument(SESSION_OVERRIDE)
                .withArgument(LOG_LEVEL, "warn")
                .withArgument(USE_DRIVERS, "uiautomator2")
                .withLogFile(new File("results/AppiumAndroidServer.log"));
        } else if (platformName.equalsIgnoreCase("ios")) {
            builder.withIPAddress("127.0.0.1")
                .withArgument(BASEPATH, "/wd/hub")
                .withArgument(SESSION_OVERRIDE)
                .withArgument(LOG_LEVEL, "warn")
                .withArgument(USE_DRIVERS, "xcuitest")
                .withLogFile(new File("results/AppiumIOSServer.log"));
        }

        service = AppiumDriverLocalService.buildService(builder);
        LOG.info("Starting Appium Server");
        service.start();
    }

    /**
     * stopServer
     * Stops the Appium Server.  Throws error on failure.
     */
    public static void stopServer() {
        try {
            LOG.info("Trying to stop the server...");
            service.stop();
            LOG.info("Success, Server stopped.");
        } catch (Exception e) {
            LOG.info("Appium server could not be stopped.");
        }
    }
}
