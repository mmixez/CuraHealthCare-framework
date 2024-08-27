package com.Cura.qa.testcases;

import com.Cura.qa.base.Base;
import com.Cura.qa.pages.HomePage;
import com.Cura.qa.pages.LogoutPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LogoutTest extends Base {

    WebDriver driver;

    public LogoutTest() {
        super();
    }

    @BeforeClass
    public void setUp() {
        driver = initializeBrowserAndOpenApplicationURL("chrome");
        LoginTest loginTest = new LoginTest();
        loginTest.setUp();
        loginTest.verifyLoginWithValidCredentials();
        driver = loginTest.getDriver();
    }

    @Test
    public void verifyLogoutSuccess() {

        LogoutPage logoutPage = new LogoutPage(driver);
        HomePage homePage = new HomePage(driver);

        logoutPage.clickHomeDropMenu();
        logoutPage.selectLogoutOption();
        String actualWelcomeMessage = homePage.retrieveHomeWelcomeMessageText();
        Assert.assertTrue(actualWelcomeMessage.contains(dataProp.getProperty("expectedWelcomeMessage")), "Welcome message is not displayed correcrlty on the page.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
