package com.mycompany.app;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mycompany.app.screens.Alerts;
import com.mycompany.app.screens.HomeScreen;
import com.mycompany.app.screens.LoginScreen;
import com.mycompany.app.screens.SecretAreaScreen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginTest extends BaseTest {
    private static final Logger LOG = LogManager.getLogger();
    private static final int MAX_CHAR = 200;

    @Test
    public final void userAndPassEmptyTest() throws Exception {
        LOG.info("Starting userAndPassEmptyTest");
        HomeScreen hs = new HomeScreen();
        hs.click(hs.logInElement);
        LoginScreen ls = new LoginScreen();
        ls.click(ls.loginButton);
        ls.getPageSource();
        Assert.assertTrue(ls.isAlert());
        if (ls.isAlert()) {
            Alerts al = new Alerts();
            al.acceptAlert();
        }
        ls.click(ls.backButton);
        hs = new HomeScreen();
        Assert.assertTrue(hs.validateScreen(hs.elementMap.get(
            "identifier"), hs.elementMap.get("idExpression")));
    }
    @Test
    public final void userNameHasSpace() {
        LOG.info("Starting userNameHasSpace");
        HomeScreen hs = new HomeScreen();
        hs.click(hs.logInElement);
        LoginScreen ls = new LoginScreen();
        ls.click(ls.usernameElement);
        ls.sendkeys(ls.usernameElement, "a lice");
        ls.click(ls.passwordElement);
        ls.sendkeys(ls.passwordElement, ls.defaultPassword);
        ls.click(ls.loginButton);
        ls.getPageSource();
        Assert.assertTrue(ls.isAlert());
        if (ls.isAlert()) {
            Alerts al = new Alerts();
            al.acceptAlert();
        }
        ls.click(ls.backButton);
        hs = new HomeScreen();
        Assert.assertTrue(hs.validateScreen(hs.elementMap.get(
            "identifier"), hs.elementMap.get("idExpression")));
    }
    @Test
    public final void userNameEmpty() {
        LOG.info("Starting userNameEmpty");
        HomeScreen hs = new HomeScreen();
        hs.click(hs.logInElement);
        LoginScreen ls = new LoginScreen();
        ls.click(ls.usernameElement);
        ls.sendkeys(ls.usernameElement, "");
        ls.click(ls.passwordElement);
        ls.sendkeys(ls.passwordElement, ls.defaultPassword);
        ls.click(ls.loginButton);
        ls.getPageSource();
        Assert.assertTrue(ls.isAlert());
        if (ls.isAlert()) {
            Alerts al = new Alerts();
            al.acceptAlert();
        }
        ls.click(ls.backButton);
        hs = new HomeScreen();
        Assert.assertTrue(hs.validateScreen(hs.elementMap.get(
            "identifier"), hs.elementMap.get("idExpression")));
    }
    @Test
    public final void passwordEmpty() {
        LOG.info("Starting passwordEmpty");
        HomeScreen hs = new HomeScreen();
        hs.click(hs.logInElement);
        LoginScreen ls = new LoginScreen();
        ls.click(ls.usernameElement);
        ls.sendkeys(ls.usernameElement, ls.defaultUser);
        ls.click(ls.passwordElement);
        ls.sendkeys(ls.passwordElement, "");
        ls.click(ls.loginButton);
        ls.getPageSource();
        Assert.assertTrue(ls.isAlert());
        if (ls.isAlert()) {
            Alerts al = new Alerts();
            al.acceptAlert();
        }
        ls.click(ls.backButton);
        hs = new HomeScreen();
        Assert.assertTrue(hs.validateScreen(hs.elementMap.get(
            "identifier"), hs.elementMap.get("idExpression")));
    }
    @Test
    public final void invalidUser() {
        LOG.info("Starting invalidUser");
        HomeScreen hs = new HomeScreen();
        hs.click(hs.logInElement);
        LoginScreen ls = new LoginScreen();
        ls.click(ls.usernameElement);
        ls.sendkeys(ls.usernameElement, "chuck");
        ls.click(ls.passwordElement);
        ls.sendkeys(ls.passwordElement, ls.defaultPassword);
        ls.click(ls.loginButton);
        ls.getPageSource();
        Assert.assertTrue(ls.isAlert());
        if (ls.isAlert()) {
            Alerts al = new Alerts();
            al.acceptAlert();
        }
        ls.click(ls.backButton);
        hs = new HomeScreen();
        Assert.assertTrue(hs.validateScreen(hs.elementMap.get(
            "identifier"), hs.elementMap.get("idExpression")));
    }
    @Test
    public final void userNameTooLong() {
        LOG.info("Starting userNameTooLong");
        HomeScreen hs = new HomeScreen();
        hs.click(hs.logInElement);
        LoginScreen ls = new LoginScreen();
        ls.click(ls.usernameElement);
        String longUsername = "c".repeat(MAX_CHAR);
        ls.sendkeys(ls.usernameElement, longUsername);
        ls.click(ls.passwordElement);
        ls.sendkeys(ls.passwordElement, ls.defaultPassword);
        ls.click(ls.loginButton);
        ls.getPageSource();
        Assert.assertTrue(ls.isAlert());
        if (ls.isAlert()) {
            Alerts al = new Alerts();
            al.acceptAlert();
        }
        ls.click(ls.backButton);
        hs = new HomeScreen();
        Assert.assertTrue(hs.validateScreen(hs.elementMap.get(
            "identifier"), hs.elementMap.get("idExpression")));
    }
    @Test
    public final void passwordTooLong() {
        LOG.info("Starting passwordTooLong");
        HomeScreen hs = new HomeScreen();
        hs.click(hs.logInElement);
        LoginScreen ls = new LoginScreen();
        ls.click(ls.usernameElement);
        String longPassword = "c".repeat(MAX_CHAR);
        ls.sendkeys(ls.usernameElement, ls.defaultUser);
        ls.click(ls.passwordElement);
        ls.sendkeys(ls.passwordElement, longPassword);
        ls.click(ls.loginButton);
        ls.getPageSource();
        Assert.assertTrue(ls.isAlert());
        if (ls.isAlert()) {
            Alerts al = new Alerts();
            al.acceptAlert();
        }
        ls.click(ls.backButton);
        hs = new HomeScreen();
        Assert.assertTrue(hs.validateScreen(hs.elementMap.get(
            "identifier"), hs.elementMap.get("idExpression")));
    }
    @Test
    public final void loginScreenTest() throws Exception {
        LOG.info("Starting loginScreenTest");
        HomeScreen hs = new HomeScreen();
        hs.click(hs.logInElement);
        LoginScreen ls = new LoginScreen();
        ls.click(ls.usernameElement);
        ls.sendkeys(ls.usernameElement, ls.defaultUser);
        ls.click(ls.passwordElement);
        ls.sendkeys(ls.passwordElement, ls.defaultPassword);
        ls.click(ls.loginButton);
        SecretAreaScreen sa = new SecretAreaScreen();
        sa.click(sa.logoutButton);
        sa.click(sa.backButton);
        sa.click(sa.backButton);
        ls = new LoginScreen();
        ls.click(ls.backButton);
        hs = new HomeScreen();
        Assert.assertTrue(hs.validateScreen(hs.elementMap.get(
            "identifier"), hs.elementMap.get("idExpression")));
    }
}
