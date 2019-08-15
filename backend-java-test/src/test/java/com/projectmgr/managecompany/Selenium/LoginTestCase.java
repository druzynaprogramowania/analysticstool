package com.projectmgr.managecompany.Selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class LoginTestCase {
    long start = System.currentTimeMillis();
    //FirefoxDriver driver = new FirefoxDriver();
    public WebDriver driver;

    @BeforeMethod
    public void setupTest(){
        System.setProperty("webdriver.gecko.driver","C:\\Praca Magisterska\\Selenium tests\\source\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("http://localhost:3000");
        //driver.navigate().to("http://localhost:3000");
    }

    @Test
    public void firstTest(){
        //System.setProperty("webdriver.gecko.driver","C:\\Praca Magisterska\\Selenium tests\\source\\geckodriver.exe");
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys("test@gmail.com");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("test123");

        long finish = System.currentTimeMillis();
        long totalTime = finish - start;

        System.out.println("Total Time for page load - " + totalTime);

    }

    @AfterMethod
    public void teardownTest (){
        //Close browser and end the session
        driver.quit();
    }

}
