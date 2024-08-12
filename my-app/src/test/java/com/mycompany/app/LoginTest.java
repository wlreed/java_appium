package com.mycompany.app;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mycompany.app.drivers.APM;
import com.mycompany.app.screens.HomeScreen;
import com.mycompany.app.screens.LoginScreen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginTest extends BaseTest {
    private static final Logger LOG = LogManager.getLogger();

    @Test
    public final void userAndPassEmptyTest() throws Exception {
        LOG.info("Starting userAndPassEmptyTest");
        HomeScreen hs = new HomeScreen();
        hs.click(hs.logInElement);
        LoginScreen ls = new LoginScreen();
        ls.click(ls.loginButton);
        LOG.info("Driver: " + APM.getDriver().getClass());
    }
    @Test
    public final void loginScreenTest() throws Exception {
        LOG.info("Starting loginScreenTest");
        HomeScreen hs = new HomeScreen();
        hs.click(hs.logInElement);
        LoginScreen ls = new LoginScreen();
        // Assert.assertTrue(
        //     hs.validateScreen(hs.elementMap.get("identifier")));
        ls.click(ls.usernameElement);
        ls.sendkeys(ls.usernameElement, ls.defaultUser);
        ls.click(ls.passwordElement);
        ls.sendkeys(ls.passwordElement, ls.defaultPassword);
        ls.click(ls.loginButton);
    }
}
