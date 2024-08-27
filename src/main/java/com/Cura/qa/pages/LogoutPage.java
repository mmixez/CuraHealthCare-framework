package com.Cura.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogoutPage {

    WebDriver driver;

    // constructor
    public LogoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // objects
    @FindBy(xpath = "//i[@class='fa fa-bars']")
    private WebElement homeDropMenu;

    @FindBy(xpath = "//a[normalize-space()='Logout']")
    private WebElement logoutOption;

    @FindBy(xpath = "//h3[normalize-space()='We Care About Your Health']")
    private WebElement homeWelcome;


    // actions
    public void clickHomeDropMenu() {
        homeDropMenu.click();
    }

    public void selectLogoutOption() {
        logoutOption.click();
    }
}


