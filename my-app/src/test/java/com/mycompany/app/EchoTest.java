package com.mycompany.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mycompany.app.screens.EchoBoxScreen;
import com.mycompany.app.screens.HomeScreen;

public class EchoTest extends BaseTest {
    private static final Logger LOG = LogManager.getLogger();
    private static final int MAX_CHAR = 200;
    
    @Test
    public final void noDataClickSave() {
        LOG.info("Starting noDataClickSave test");
        HomeScreen hs = new HomeScreen();
        hs.click(hs.echoBoxElement);
        EchoBoxScreen ebs = new EchoBoxScreen();
        // Current value may be left over from earlier test.
        // this test ensures that value does not change.
        String expected = ebs.savedMessageElement.getText();
        ebs.click(ebs.messageSaveButton);
        Assert.assertEquals(ebs.savedMessageElement.getText(), expected);
        ebs.click(ebs.backButton);
    }

    @Test
    public final void dataWithSpace() {
        LOG.info("Starting dataWithSpace test");
        HomeScreen hs = new HomeScreen();
        hs.click(hs.echoBoxElement);
        EchoBoxScreen ebs = new EchoBoxScreen();
        ebs.sendkeys(ebs.messageInputField, "Hello dataWithSpace!");
        ebs.click(ebs.messageSaveButton);
        Assert.assertEquals(ebs.savedMessageElement.getText(), "Hello dataWithSpace!");
        ebs.click(ebs.backButton);
    }

    @Test
    public final void longData() {
        LOG.info("Starting longData test");
        HomeScreen hs = new HomeScreen();
        hs.click(hs.echoBoxElement);
        EchoBoxScreen ebs = new EchoBoxScreen();
        String longDataString = "c".repeat(MAX_CHAR);
        ebs.sendkeys(ebs.messageInputField, longDataString);
        ebs.click(ebs.messageSaveButton);
        Assert.assertEquals(ebs.savedMessageElement.getText(), longDataString);
        ebs.click(ebs.backButton);
    }

    @Test
    public final void dataPersists() {
        LOG.info("Starting dataPersists test");
        HomeScreen hs = new HomeScreen();
        hs.click(hs.echoBoxElement);
        EchoBoxScreen ebs = new EchoBoxScreen();
        ebs.sendkeys(ebs.messageInputField, "Hello dataPersists!");
        ebs.click(ebs.messageSaveButton);
        Assert.assertEquals(ebs.savedMessageElement.getText(), "Hello dataPersists!");
        ebs.click(ebs.backButton);
        hs = new HomeScreen();
        hs.click(hs.echoBoxElement);
        ebs = new EchoBoxScreen();
        Assert.assertEquals(ebs.savedMessageElement.getText(), "Hello dataPersists!");
        ebs.click(ebs.backButton);
    }

    @Test
    public final void validData() {
        LOG.info("Starting validData");
        HomeScreen hs = new HomeScreen();
        hs.click(hs.echoBoxElement);
        EchoBoxScreen ebs = new EchoBoxScreen();
        ebs.click(ebs.messageInputField);
        ebs.sendkeys(ebs.messageInputField, "Hello validData!\n");
        ebs.click(ebs.messageSaveButton);
        Assert.assertEquals(ebs.savedMessageElement.getText(), "Hello validData!");
        ebs.click(ebs.backButton);
    }
}
