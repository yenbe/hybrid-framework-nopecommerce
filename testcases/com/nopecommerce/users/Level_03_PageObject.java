package com.nopecommerce.users;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.CustomerInfoPageObject;
import pageObjects.nopCommerce.HomePageObject;
import pageObjects.nopCommerce.LoginPageObject;
import pageObjects.nopCommerce.RegisterPageObject;

import java.time.Duration;

public class Level_03_PageObject extends BaseTest {

    WebDriver driver;
    String email = "yen" + generateFakeNumber() + "@gmail.com";
    HomePageObject homePage;
    LoginPageObject loginPage;
    RegisterPageObject registerPage;
    CustomerInfoPageObject customerPage;
    String firstName, lastName, password;

    @BeforeClass
    public void beforeClass() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--user-data-dir=C:/Users/Admin/AppData/Local/Microsoft/Edge/User Data/");
        edgeOptions.addArguments("--profile-directory=Profile 1");
        firstName = "Yen";
        lastName = "Dao";
        password = "yen123@yen";
        driver = new EdgeDriver(edgeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://demo.nopcommerce.com/");
        homePage = new HomePageObject(driver);
    }

    @Test
    public void TC_01_Register() {
        homePage.clickToRegisterLink();
        // Tu page A sang B (Home sang Register) : khoi tao
        registerPage = new RegisterPageObject(driver);

        registerPage.sendkeysToFirstNameTextbox(firstName);
        registerPage.sendkeysToLastNameTextbox(lastName);
        registerPage.sendkeysToEmailTextbox(email);
        registerPage.sendkeysToPasswordTextbox(password);
        registerPage.sendkeysToConfirmPasswordTextbox(password);
        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
        registerPage.clickLogoutLink();

        homePage = new HomePageObject(driver);

    }
    @Test
    public void TC_02_Login() {

        homePage.clickLoginLink();

        loginPage = new LoginPageObject(driver);

        loginPage.sendkeyToEmailTextbox(email);
        loginPage.sendkeyToPasswordTextbox(password);
        loginPage.clickToLoginButton();

        homePage = new HomePageObject(driver);

    }
    @Test
    public void TC_03_MyAccount() {

        homePage.clickMyAccountLink();

        customerPage = new CustomerInfoPageObject(driver);
        Assert.assertEquals(customerPage.getFirstNameTextboxValue(),firstName);
        Assert.assertEquals(customerPage.getLastNameTextboxValue(),lastName);
        Assert.assertEquals(customerPage.getEmailTextboxValue(),email);

    }

    @AfterClass
    public void quitBrowser(){
        driver.quit();
    }
}
