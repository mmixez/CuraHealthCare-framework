package com.Cura.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HistoryPage {

    WebDriver driver;

    public HistoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h2[normalize-space()='History']")
    private WebElement historyPageTitle;

    @FindBy(xpath = "//p[@id='facility']")
    private WebElement historyFacility;

    @FindBy(xpath = "//p[@id='program']")
    private WebElement historyProgram;

    @FindBy(xpath = "//p[@id='hospital_readmission']")
    private WebElement historyReadmission;

    @FindBy(xpath = "//div[@class='panel-heading']")
    private WebElement historyVisitDate;

    @FindBy(xpath = "//p[@id='comment']")
    private WebElement historyComment;


    public String retrieveHistoryPageTitleText() {
        return historyPageTitle.getText();
    }

    public boolean isHistoryTitleDisplayed() {
        return historyPageTitle.isDisplayed();
    }

    public String getHistoryFacility() {
        return historyFacility.getText();
    }

    public String getHistoryProgram() {
        return historyProgram.getText();
    }

    public String getHistoryVisitDate() {
        return historyVisitDate.getText();
    }

    public String getHistoryComment() {
        return historyComment.getText();
    }

    public boolean isReadmissionChecked() {
        return historyReadmission.isSelected();
    }

}
