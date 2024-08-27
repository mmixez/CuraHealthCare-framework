package com.Cura.qa.testcases;

import com.Cura.qa.base.Base;
import com.Cura.qa.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class HomeTest extends Base {


    WebDriver driver;

    public HomeTest() {
        super();
    }

    @BeforeMethod
    public void setup() {
        driver = initializeBrowserAndOpenApplicationURL("chrome");

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void verifyRedirectingHomePageTest() {

        HomePage homePage = new HomePage(driver);


        homePage.clickHomeDropMenu();
        homePage.selectLoginOption();

        String actualWelcomeMessage = homePage.retrieveHomeWelcomeMessageText();
        Assert.assertTrue(actualWelcomeMessage.contains(dataProp.getProperty("expectedWelcomeMessage")), "Welcome message is not displayed correcrlty on the page.");

    }


}
