package com.nopecommerce.users;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Level_02_Apply_BasePage_I {

    WebDriver driver;
    BasePage basePage;
    String email = "yen" + new Random().nextInt(999) + "@gmail.com";

    @BeforeClass
    public void beforeClass() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--user-data-dir=C:/Users/Admin/AppData/Local/Microsoft/Edge/User Data/");
        edgeOptions.addArguments("--profile-directory=Profile 1");
        driver = new EdgeDriver(edgeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        basePage = new BasePage();
        basePage.openUrl(driver, "https://demo.nopcommerce.com/");
    }

    @Test
    public void TC_01() {
        basePage.clickToElement(driver, "//a[@class='ico-register']");
        basePage.clickToElement(driver,"//button[@id='register-button']");
        Assert.assertEquals(basePage.getTextElement(driver,"//span[@id='FirstName-error']"),"First name is required.");
        Assert.assertEquals(basePage.getTextElement(driver,"//span[@id='LastName-error']"),"Last name is required.");

    }
    @AfterClass
    public void quitBrowser(){
        driver.quit();
    }
}
