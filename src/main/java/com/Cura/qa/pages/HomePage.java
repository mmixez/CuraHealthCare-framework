package com.Cura.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    WebDriver driver;

    // constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // objects
    @FindBy(xpath = "//i[@class='fa fa-bars']")
    private WebElement homeDropMenu;


    @FindBy(xpath = "//a[normalize-space()='Login']")
    private WebElement loginOption;

    @FindBy(xpath = "//h3[normalize-space()='We Care About Your Health']")
    private WebElement homeWelcome;


    // actions
    public void clickHomeDropMenu() {
        homeDropMenu.click();
    }

    public void selectLoginOption() {
        loginOption.click();
    }

    public String retrieveHomeWelcomeMessageText() {
        return homeWelcome.getText();
    }

}
