package com.mycompany.app;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mycompany.app.screens.HomeScreen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class APMTest extends BaseTest{
    private static final Logger LOG = LogManager.getLogger();

    @Test
    public void APMServerTest() {
        LOG.info("Server and iOS Driver started successfully!!");
    }
    @Test
    public void APMHomeScreenTest() throws Exception {
        HomeScreen homeScreen = new HomeScreen();
        //Assert.assertTrue(homeScreen.validateScreen(homeScreen.elementMap.get("identifier")));
        homeScreen.click(homeScreen.logInElement);
        Thread.sleep(5000);
    }
}
