package com.projectmgr.managecompany.Selenium;

import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

public class UpdateItemTestCase {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    long start = System.currentTimeMillis();

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver","C:\\Praca Magisterska\\Selenium tests\\source\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("http://localhost:3000");
    }

    @Test
    public void testUpdateItemTestCase() throws Exception {
        driver.get("http://localhost:3000/");
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys("test@gmail.com");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("test123");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Log In'])[1]/following::input[3]")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Delete'])[12]/following::li[1]")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Logout'])[1]/following::div[3]")).click();
        driver.findElement(By.name("quantity")).clear();
        driver.findElement(By.name("quantity")).sendKeys("300");
        driver.findElement(By.name("description")).click();
        driver.findElement(By.name("end_date")).click();
        driver.findElement(By.name("end_date")).clear();
        driver.findElement(By.name("end_date")).sendKeys("2019-09-28");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Estimated End Date'])[1]/following::input[2]")).click();
        driver.findElement(By.linkText("Logout")).click();

        long finish = System.currentTimeMillis();
        long totalTime = finish - start;

        System.out.println("Total Time for page load - " + totalTime);
    }

    @AfterClass(alwaysRun = true)
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
