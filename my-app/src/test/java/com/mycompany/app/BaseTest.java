package com.mycompany.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static com.mycompany.app.drivers.APM.getInstance;
import static com.mycompany.app.drivers.APM.quitSession;

/**
 * BaseIOSTest
 * Contains @BeforeClass and @AfterClass methods for iOS testing
 */
public class BaseTest {
    private static final Logger LOG = LogManager.getLogger();

    @BeforeClass
    public final void testSetup()  {
        LOG.info("Performing getInstance()");
        getInstance();
    }

    @AfterClass
    public final void tearDown() {
        LOG.info("Performing quitSession()");
        quitSession();
    }
}
