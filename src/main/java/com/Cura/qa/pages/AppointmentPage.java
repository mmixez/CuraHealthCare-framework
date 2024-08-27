package com.Cura.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AppointmentPage {

    WebDriver driver;

    public AppointmentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@id='btn-make-appointment']")
    private WebElement makeAppointmentButton;

    @FindBy(xpath = "//select[@id='combo_facility']")
    private WebElement selectFacility;

    @FindBy(xpath = "//input[@id='chk_hospotal_readmission']")
    private WebElement chkReadmission;

    @FindBy(xpath = "//label[normalize-space()='Medicare']")
    private WebElement medicareOption;

    @FindBy(xpath = "//label[normalize-space()='Medicaid']")
    private WebElement medicaidOption;

    @FindBy(xpath = "//label[normalize-space()='None']")
    private WebElement noneOption;

    @FindBy(xpath = "//input[@id='txt_visit_date']")
    private WebElement selectVisitDate;

    @FindBy(xpath = "//textarea[@id='txt_comment']")
    private WebElement fillComment;

    @FindBy(xpath = "//button[@id='btn-book-appointment']")
    private WebElement bookAppointmentButton;

    @FindBy(xpath = "//p[@class='lead']")
    private WebElement appointConfirmation;

//
//public boolean getMakeAppointmentPageDisplayed() {
//    boolean displayStatus = driver.findElement(By.id ("//h2[normalize-space()='Make Appointment']")).isDisplayed();
//    return displayStatus;
//}

    public void clickMakeAppointment() {
        makeAppointmentButton.click();
    }

    public void setSelectFacility(String facility) {
        WebElement drpFacilityEle = selectFacility;
        Select selectedFaility = new Select(drpFacilityEle);
        selectedFaility.selectByValue(facility);
    }

    public void checkReadmission() {
        chkReadmission.click();
    }

    public void setSelectProgram(String program) {

        if (program.equalsIgnoreCase("medicare")) {
            medicareOption.click();
        } else if (program.equalsIgnoreCase("medicaid")) {
            medicaidOption.click();
        } else if (program.equalsIgnoreCase("none")) {
            noneOption.click();
        } else {
            throw new IllegalArgumentException("Invalid program option: " + program);
        }

    }

    public void setSelectVisitDate(String date) {
        selectVisitDate.sendKeys(date);
    }

    public void setFillComment(String comment) {
        fillComment.sendKeys("Hello, would Dr. Kang be available on the appointment day?");
    }

    public void clickBookAppointmentBtn() {
        bookAppointmentButton.click();
    }

    public String retrieveAppointmentConfirmationMessage() {
        return appointConfirmation.getText();
    }

    // Methods to retrieve appointment details
    public String getSelectedFacility() {
        return new Select(selectFacility).getFirstSelectedOption().getText();
    }

    public boolean isReadmissionChecked() {
        return chkReadmission.isSelected();
    }

    public String getSelectedProgram() {
        if (medicareOption.isSelected()) return "Medicare";
        if (medicaidOption.isSelected()) return "Medicaid";
        if (noneOption.isSelected()) return "None";
        return null;
    }

    public String getSelectedVisitDate() {
        return selectVisitDate.getAttribute("value");
    }

    public String getFilledComment() {
        return fillComment.getAttribute("value");
    }

}
