package com.mycompany.app;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mycompany.app.screens.HomeScreen;
import com.mycompany.app.screens.LoginScreen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginTest extends BaseTest{
    private static final Logger LOG = LogManager.getLogger();

    @Test
    public void APMHomeScreenTest() throws Exception {
        HomeScreen hs = new HomeScreen();
        //Assert.assertTrue(homeScreen.validateScreen(homeScreen.elementMap.get("identifier")));
        hs.click(hs.logInElement);
        LoginScreen ls = new LoginScreen();
        ls.click(ls.usernameElement);
        ls.sendkeys(ls.usernameElement, ls.defaultUser);
        ls.click(ls.passwordElement);
        ls.sendkeys(ls.passwordElement, ls.defaultPassword);
        ls.click(ls.loginButton);
    }
}
