package pageFactory.nopCommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageUIs.nopCommerce.CustomerPageUI;

public class CustomerInforPageObject extends BasePage {
    private WebDriver driver;

    @FindBy(id = "FirstName")
    private WebElement firstNameTextbox;

    @FindBy(id = "LastName")
    private WebElement lastNameTextbox;

    @FindBy(id = "Email")
    private WebElement emailTextbox;

    public CustomerInforPageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public String getFirstNameTextboxValue() {
        waitForElementVisible(driver, firstNameTextbox);
        return getElementAttribue(firstNameTextbox, "value");
    }

    public String getLastNameTextboxValue() {
        waitForElementVisible(driver, lastNameTextbox);
        return getElementAttribue(lastNameTextbox, "value");
    }

    public String getEmailTextboxValue() {
        waitForElementVisible(driver, emailTextbox);
        return getElementAttribue(emailTextbox, "value");
    }
}
