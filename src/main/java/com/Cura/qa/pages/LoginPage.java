package com.Cura.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;


    // constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='txt-username']")
    private WebElement userNameField;

    @FindBy(xpath = "//input[@id='txt-password']")
    private WebElement userPasswordField;

    @FindBy(xpath = "//button[@id='btn-login']")
    private WebElement loginButton;

    @FindBy(xpath = "//h2[normalize-space()='Make Appointment']")
    private WebElement makeAppointment;

    @FindBy(xpath = "//p[@class=\"lead text-danger\"]")
    private WebElement loginFailedWarning;

    // actions
    public void enterUserName(String userNameText) {
        userNameField.sendKeys(userNameText);
    }

    public void enterPassword(String passwordText) {
        userPasswordField.sendKeys(passwordText);
    }

    public void clickOnLoginButton() {
        loginButton.click();
    }

    public String retrieveMakeAppointmentText() {
        return makeAppointment.getText();
    }

    public String retrieveLoginFailedWarningMessageText() {
        return loginFailedWarning.getText();
    }
}
