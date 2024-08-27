package com.Cura.qa.testcases;

import com.Cura.qa.base.Base;
import com.Cura.qa.pages.HistoryPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class HistoryTest extends Base {


    WebDriver driver;
    String storedFacility;
    String storedProgram;
    String storedVisitDate;
    String storedComment;
    boolean isReadmissionChecked;

    public HistoryTest() {
        super();
    }

    @BeforeClass
    public void setUp() throws InterruptedException {
        MakeAppointmentTest makeAppointmentTest = new MakeAppointmentTest();
        makeAppointmentTest.setUp();
        makeAppointmentTest.verifyMakeAppointment();
        driver = makeAppointmentTest.getDriver();

        //Store appointment details from MakeAppointmentTest
        storedFacility = makeAppointmentTest.getStoredFacility();
        storedProgram = makeAppointmentTest.getStoredProgram();
        storedVisitDate = makeAppointmentTest.getStoredVisitDate();
        storedComment = makeAppointmentTest.getStoredComment();
        isReadmissionChecked = makeAppointmentTest.getStoredReadmissionStatus();


        driver.findElement(By.xpath("//i[@class='fa fa-bars']")).click();
        driver.findElement(By.xpath("//a[normalize-space()='History']")).click();

    }

    @Test(priority = 1)
    public void verifyHistoryTitleIsDisplayed() {
        HistoryPage historyPage = new HistoryPage(driver);
        Assert.assertTrue(
                historyPage.isHistoryTitleDisplayed() && "History".equals(historyPage.retrieveHistoryPageTitleText()),
                "History title is either not displayed or the text is incorrect.");


    }

    @Test(priority = 2)
    public void verifyAppointmentDetailsInHistory() {
        HistoryPage historyPage = new HistoryPage(driver);

        Assert.assertEquals(storedFacility, historyPage.getHistoryFacility(), "Facility details do not match.");
        Assert.assertEquals(storedProgram, historyPage.getHistoryProgram(), "Program details do not match.");
        Assert.assertEquals(storedVisitDate, historyPage.getHistoryVisitDate(), "Visit date details do not match.");
        Assert.assertEquals(storedComment, historyPage.getHistoryComment(), "Comment details do not match.");

        boolean isReadmissionDisplayed = historyPage.isReadmissionChecked();
        Assert.assertEquals(isReadmissionChecked, isReadmissionChecked, "Readmission status does not match.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
