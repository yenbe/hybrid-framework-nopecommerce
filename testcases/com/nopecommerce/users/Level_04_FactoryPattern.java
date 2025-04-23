package com.nopecommerce.users;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.*;

import java.time.Duration;

public class Level_04_FactoryPattern extends BaseTest {

    WebDriver driver;
    String email = "yen" + generateFakeNumber() + "@gmail.com";
    HomePageObject homePage;
    LoginPageObject loginPage;
    RegisterPageObject registerPage;
    CustomerInforPageObject customerPage;
    String firstName, lastName, password;

    @Parameters({"url", "browser"})
    @BeforeClass
    public void beforeClass(String urlValue, String browserName) {
        driver = getBrowserDriver(urlValue, browserName);


        firstName = "Yen";
        lastName = "Dao";
        password = "yen123@yen";
        homePage = (HomePageObject) PageManager.getPage(driver, "HomePage");
    }

    @Test
    public void TC_01_Register() {
        homePage.clickToRegisterLink();
        // Tu page A sang B (Home sang Register) : khoi tao
        registerPage = (RegisterPageObject) PageManager.getPage(driver, "RegisterPage");

        registerPage.sendkeysToFirstNameTextbox(firstName);
        registerPage.sendkeysToLastNameTextbox(lastName);
        registerPage.sendkeysToEmailTextbox(email);
        registerPage.sendkeysToPasswordTextbox(password);
        registerPage.sendkeysToConfirmPasswordTextbox(password);
        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
        registerPage.clickLogoutLink();

        homePage = (HomePageObject) PageManager.getPage(driver, "HomePage");

    }
    @Test
    public void TC_02_Login() {

        homePage.clickLoginLink();

        loginPage = (LoginPageObject) PageManager.getPage(driver, "LoginPage");

        loginPage.sendkeyToEmailTextbox(email);
        loginPage.sendkeyToPasswordTextbox(password);
        loginPage.clickToLoginButton();

        homePage = (HomePageObject) PageManager.getPage(driver, "HomePage");

    }
    @Test
    public void TC_03_MyAccount() {

        homePage.clickMyAccountLink();

        customerPage = (CustomerInforPageObject) PageManager.getPage(driver,"CustomerInfor");
        Assert.assertEquals(customerPage.getFirstNameTextboxValue(),firstName);
        Assert.assertEquals(customerPage.getLastNameTextboxValue(),lastName);
        Assert.assertEquals(customerPage.getEmailTextboxValue(),email);

    }
    

    @AfterClass
    public void quitBrowser(){
        driver.quit();
    }
}
