package com.mycompany.app;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mycompany.app.screens.HomeScreen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class APMTest extends BaseTest {
    private static final Logger LOG = LogManager.getLogger();

    @Test
    public final void apmServerTest() {
        LOG.info("Server and iOS Driver started successfully!!");
    }
    @Test
    public final void apmHomeScreenTest() {
        HomeScreen hs = new HomeScreen();
        Assert.assertTrue(hs.validateScreen(hs.elementMap.get("identifier"), hs.elementMap.get("idExpression")));
    }
}
