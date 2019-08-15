package com.projectmgr.managecompany.Selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class DeleteItemTestCase {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    long start = System.currentTimeMillis();

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver","C:\\Praca Magisterska\\Selenium tests\\source\\geckodriver.exe");
        driver = new FirefoxDriver();
        baseUrl = "http://localhost:3000";
    }

    @Test
    public void testDeleteItemTestCase() throws Exception {
        driver.get("http://localhost:3000/");
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys("test@gmail.com");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("test123");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Log In'])[1]/following::input[3]")).click();
        acceptNextAlert = true;
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Samsung s4 test'])[1]/following::li[1]")).click();
        assertTrue(closeAlertAndGetItsText().matches("^Are you sure[\\s\\S] This operation will delete the item and all the data related to it$"));

        long finish = System.currentTimeMillis();
        long totalTime = finish - start;

        System.out.println("Total Time for page load - " + totalTime);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
