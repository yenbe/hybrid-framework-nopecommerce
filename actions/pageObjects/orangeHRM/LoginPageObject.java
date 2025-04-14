package pageObjects.orangeHRM;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.orangeHRM.LoginPageUI;

public class LoginPageObject extends BasePage {
    private WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void sendkeyToUsernameTextbox(String usernameValue) {
        waitForElementVisible(driver, LoginPageUI.USENAME_TEXTBOX);
        sendkeyToElement(driver, LoginPageUI.USENAME_TEXTBOX, usernameValue);
    }

    public void sendkeyToPasswordTextbox(String passwordValue) {
        waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, passwordValue);
    }

    public void clickLoginButton() {
        waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver,LoginPageUI.LOGIN_BUTTON );
    }
}
