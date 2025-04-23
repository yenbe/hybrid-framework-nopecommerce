package pageFactory.nopCommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import pageUIs.nopCommerce.RegisterPageUI;

public class RegisterPageObject extends BasePage {
    private WebDriver driver;
    // 1- Dinh nghia Element trong chinh Class nay
    // Ko dua ra ngoai1 Class khac/package khac

    @FindBy(how = How.XPATH, using = "//input[@id='FirstName']")
    private WebElement firstNameTextbox;

    @FindBy(how = How.ID, using = "LastName")
    private WebElement lastNameTextbox;

    @FindBy(how = How.CSS, using = "input[id='Email']")
    private WebElement emailTextbox;

    @FindBy(xpath = "//input[@id='Password']")
    private WebElement passwordTextbox;

    @FindBy(id = "ConfirmPassword")
    private WebElement confirmPasswordTextbox;

    @FindBy(id = "register-button")
    private WebElement registerButton;

    @FindBy(css = "div.result")
    private WebElement registerSuccessMessage;

    @FindBy(xpath = "//a[@class='ico-logout']")
    private WebElement logoutLink;

    // 2- Init cac Element len
    public RegisterPageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // 3- Action nó tuong tu ten ham nhu dung voi Page Object
    public void sendkeysToFirstNameTextbox(String firstName) {
        waitForElementVisible(driver,firstNameTextbox );
        sendkeyToElement(firstNameTextbox, firstName);
    }
    public void sendkeysToLastNameTextbox(String lastName) {
        waitForElementVisible(driver,lastNameTextbox );
        sendkeyToElement(lastNameTextbox, lastName);
    }
    public void sendkeysToEmailTextbox(String emailAddress) {
        waitForElementVisible(driver,emailTextbox );
        sendkeyToElement(emailTextbox, emailAddress);
    }
    public void sendkeysToPasswordTextbox(String password) {
        waitForElementVisible(driver,passwordTextbox );
        sendkeyToElement(passwordTextbox, password);
    }
    public void sendkeysToConfirmPasswordTextbox(String confirmPassword) {
        waitForElementVisible(driver,confirmPasswordTextbox );
        sendkeyToElement(confirmPasswordTextbox, confirmPassword);
    }
    public void clickToRegisterButton() {
        waitForElementClickable(driver,registerButton);
        clickToElement(registerButton);
    }
    public String getRegisterSuccessMessage() {
        waitForElementVisible(driver,registerSuccessMessage);
        return getTextElement(registerSuccessMessage);
    }
    public void clickLogoutLink() {
        waitForElementClickable(driver,logoutLink);
        clickToElement(logoutLink);
    }

}
