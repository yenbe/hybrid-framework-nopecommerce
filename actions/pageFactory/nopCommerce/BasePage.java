package pageFactory.nopCommerce;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    public static commons.BasePage getBasePage(){
        return new commons.BasePage();
    }
    public void openUrl(WebDriver driver, String pageUrl) {
        driver.get(pageUrl);
    }
    public String getDomain(WebDriver driver) {
        return (String) ((JavascriptExecutor) driver).executeScript("return document.domain;");
    }
    private Alert waitToAlertPresence(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert(WebDriver driver) {
        waitToAlertPresence(driver).accept();
    }
    public void cancelAlert(WebDriver driver) {
        waitToAlertPresence(driver).dismiss();
    }
    public void sendkeyTolAlert(WebDriver driver, String valueToSend) {
        waitToAlertPresence(driver).sendKeys(valueToSend);
    }
    public String getlAlertText(WebDriver driver) {
        return waitToAlertPresence(driver).getText();
    }

    // Ham thao tac voi element

    public void clickToElement(WebElement element) {
        element.click();
    }
    public void sendkeyToElement(WebElement element, String valueToSend) {
        element.clear();
        element.sendKeys(valueToSend);
    }
    public String getTextElement(WebElement element) {
        return element.getText();
    }
    public String getElementAttribue(WebElement element, String attributeName) {
        String attribute = element.getDomAttribute(attributeName);
        return attribute;
    }
    public void waitForElementVisible(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.visibilityOf(element));
    }
    public void waitForListElementVisible(WebDriver driver, List<WebElement> elements) {
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfAllElements(elements));
    }
    public void waitForElementClickable(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(element));
    }
    private long LONG_TIMEOUT = 30;

}
