package com.mycompany.app;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static com.mycompany.app.drivers.APM.getInstance;
import static com.mycompany.app.drivers.APM.quitSession;

/**
 * BaseIOSTest
 * Contains @BeforeClass and @AfterClass methods for iOS testing
 */
public class BaseTest {
    @BeforeClass
    public void testSetup()  {
        getInstance();
    }

    @AfterClass
    public void tearDown() {
        quitSession ();
    }
}
