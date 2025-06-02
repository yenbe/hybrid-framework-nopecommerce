package pageObjects.nopCommerce.sideBar;

import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.sideBar.CustomerPageUI;

public class CustomerInforPageObject extends SidebarPageObject {
    private WebDriver driver;

    public CustomerInforPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public String getFirstNameTextboxValue() {
        waitForElementVisible(driver, CustomerPageUI.FIRST_NAME_TEXTBOX);
        return getElementAttribue(driver, CustomerPageUI.FIRST_NAME_TEXTBOX, "value");
    }

    public String getLastNameTextboxValue() {
        waitForElementVisible(driver, CustomerPageUI.LAST_NAME_TEXTBOX);
        return getElementAttribue(driver, CustomerPageUI.LAST_NAME_TEXTBOX, "value");
    }

    public String getEmailTextboxValue() {
        waitForElementVisible(driver, CustomerPageUI.EMAIL_TEXTBOX);
        return getElementAttribue(driver, CustomerPageUI.EMAIL_TEXTBOX, "value");
    }


}
