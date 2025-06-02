package com.nopecommerce.users;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.*;
import pageObjects.nopCommerce.sideBar.CustomerInforPageObject;

public class Level_04_FactoryPattern extends BaseTest {

    WebDriver driver;
    String email = "yen" + generateFakeNumber() + "@gmail.com";
    UserHomePageObject homePage;
    UserLoginPageObject loginPage;
    UserRegisterPageObject registerPage;
    CustomerInforPageObject customerPage;
    String firstName, lastName, password;

    @Parameters({"url", "browser"})
    @BeforeClass
    public void beforeClass(String urlValue, String browserName) {
        driver = getBrowserDriver(urlValue, browserName);


        firstName = "Yen";
        lastName = "Dao";
        password = "yen123@yen";
        homePage = (UserHomePageObject) PageManager.getPage(driver, "HomePage");
    }

    @Test
    public void TC_01_Register() {
        homePage.clickToRegisterLink();
        // Tu page A sang B (Home sang Register) : khoi tao
        registerPage = (UserRegisterPageObject) PageManager.getPage(driver, "RegisterPage");

        registerPage.sendkeysToFirstNameTextbox(firstName);
        registerPage.sendkeysToLastNameTextbox(lastName);
        registerPage.sendkeysToEmailTextbox(email);
        registerPage.sendkeysToPasswordTextbox(password);
        registerPage.sendkeysToConfirmPasswordTextbox(password);
        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
        registerPage.clickLogoutLinkAtUserSite(driver);
        homePage = (UserHomePageObject) PageManager.getPage(driver, "HomePage");

    }
    @Test
    public void TC_02_Login() {

        homePage.clickLoginLink();

        loginPage = (UserLoginPageObject) PageManager.getPage(driver, "LoginPage");

        loginPage.sendkeyToEmailTextbox(email);
        loginPage.sendkeyToPasswordTextbox(password);
        loginPage.clickToLoginButton();

        homePage = (UserHomePageObject) PageManager.getPage(driver, "HomePage");

    }
    @Test
    public void TC_03_MyAccount() {

        homePage.clickMyAccountLinkAtUserSite(driver);

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
