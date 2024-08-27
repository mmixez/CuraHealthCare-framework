package com.Cura.qa.testcases;

import com.Cura.qa.base.Base;
import com.Cura.qa.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest extends Base {

    WebDriver driver;

    public LoginTest() {
        super();
    }

    @BeforeMethod
    public void setUp() {

        driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
        driver.findElement(By.xpath("//i[@class='fa fa-bars']")).click();
        driver.findElement(By.xpath("//a[normalize-space()='Login']")).click();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }


    @Test(priority = 1)
    public void verifyLoginWithValidCredentials() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUserName(prop.getProperty("validUserName"));
        loginPage.enterPassword(prop.getProperty("validPassword"));
        loginPage.clickOnLoginButton();

        String actualMakeAppointmentButton = loginPage.retrieveMakeAppointmentText();
        Assert.assertTrue(actualMakeAppointmentButton.contains(dataProp.getProperty("expectedMakeAppointmentButtonMessage")), "Make Appointment Button is not present");

    }


    @Test(priority = 2)
    public void verifyLoginWithValidUserNameAndInvalidPassword() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUserName(prop.getProperty("validUserName"));
        loginPage.enterPassword(prop.getProperty("invalidPassword"));
        loginPage.clickOnLoginButton();

        String actualLoginFailedWarningMessage = loginPage.retrieveLoginFailedWarningMessageText();
        Assert.assertTrue(actualLoginFailedWarningMessage.contains(dataProp.getProperty("expectedLoginFailedWarningMessage")), "Expected warning message is not displayed");

    }

    @Test(priority = 3)
    public void verifyLoginWithInvalidUserNameAndValidPassword() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUserName(prop.getProperty("invalidUserName"));
        loginPage.enterPassword(prop.getProperty("validPassword"));
        loginPage.clickOnLoginButton();

        String actualLoginFailedWarningMessage = loginPage.retrieveLoginFailedWarningMessageText();
        Assert.assertTrue(actualLoginFailedWarningMessage.contains(dataProp.getProperty("expectedLoginFailedWarningMessage")), "Expected warning message is not displayed");

    }
}
