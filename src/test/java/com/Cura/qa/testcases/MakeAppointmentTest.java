package com.Cura.qa.testcases;

import com.Cura.qa.base.Base;
import com.Cura.qa.pages.AppointmentPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class MakeAppointmentTest extends Base {

    WebDriver driver;
    private static String storedFacility;
    private static String storedProgram;
    private static String storedVisitDate;
    private static String storedComment;
    static boolean isReadmissionChecked;

    public MakeAppointmentTest() {
        super();
    }

    @BeforeMethod
    public void setUp() {
        driver = initializeBrowserAndOpenApplicationURL("chrome");
        AppointmentPage appointmentPage = new AppointmentPage(driver);
        appointmentPage.clickMakeAppointment();

        driver.findElement(By.xpath("//input[@id='txt-username']")).sendKeys(prop.getProperty("validUserName"));
        driver.findElement(By.xpath("//input[@id='txt-password']")).sendKeys(prop.getProperty("validPassword"));
        driver.findElement(By.xpath("//button[@id='btn-login']")).click();
    }

    @Test
    public void verifyMakeAppointment() throws InterruptedException {

        //select from the dropdown
        AppointmentPage appointmentPage = new AppointmentPage(driver);

        //select facility
        appointmentPage.setSelectFacility(dataProp.getProperty("selectedFacility"));

        //checkbox for readmission
        appointmentPage.checkReadmission();

        //checkbox for programs
        appointmentPage.setSelectProgram(dataProp.getProperty("selectedProgram"));

        //visit date
        appointmentPage.setSelectVisitDate(dataProp.getProperty("selectedDate"));

        //comment
        appointmentPage.setFillComment(dataProp.getProperty("selectedComment"));
        appointmentPage.clickBookAppointmentBtn();

        //appointment confirmation
        String actualAppointmentConfirmationMessage = appointmentPage.retrieveAppointmentConfirmationMessage();
        Assert.assertTrue(actualAppointmentConfirmationMessage.contains(dataProp.getProperty("expectedAppointmentConfirmationMessage")));

        // store appointment details
        storeAppointmentDetails(appointmentPage);

    }

    public void storeAppointmentDetails(AppointmentPage appointmentPage) {
        try {

            // Print out the details to ensure they are being retrieved correctly
            try {
                WebElement facilityElement = driver.findElement(By.xpath("//p[@id='facility']"));
                String facility = facilityElement.getText();
                System.out.println("Facility: " + facility);
                storedFacility = facility;
            } catch (NoSuchElementException e) {
                System.out.println("Facility element not found.");
            }

            try {
                WebElement programElement = driver.findElement(By.xpath("//p[@id='program']"));
                String program = programElement.getText();
                System.out.println("Program: " + program);
                storedProgram = program;
            } catch (NoSuchElementException e) {
                System.out.println("Program element not found.");
            }

            try {
                WebElement visitDateElement = driver.findElement(By.xpath("//p[@id='visit_date']"));
                String visitDate = visitDateElement.getText();
                System.out.println("Visit Date: " + visitDate);
                storedVisitDate = visitDate;
            } catch (NoSuchElementException e) {
                System.out.println("Visit Date element not found.");
            }

            try {
                WebElement commentElement = driver.findElement(By.xpath("//p[@id='comment']"));
                String comment = commentElement.getText();
                System.out.println("Comment: " + comment);
                storedComment = comment;
            } catch (NoSuchElementException e) {
                System.out.println("Comment element not found.");
            }

            try {
                WebElement readmissionElement = driver.findElement(By.xpath("//p[@id='hospital_readmission']"));
                boolean readmissionChecked = readmissionElement.getText().equalsIgnoreCase("Yes");
                System.out.println("Readmission Checked: " + readmissionChecked);
                isReadmissionChecked = readmissionChecked;
            } catch (NoSuchElementException e) {
                System.out.println("Readmission element not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getStoredFacility() {
        return storedFacility;
    }

    public static String getStoredProgram() {
        return storedProgram;
    }

    public static String getStoredVisitDate() {
        return storedVisitDate;
    }

    public static String getStoredComment() {
        return storedComment;
    }

    public static boolean getStoredReadmissionStatus() {
        return isReadmissionChecked;
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}





