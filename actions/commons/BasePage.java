package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    // Chứa các commons (reuseable) function cho các class bên Page Object

    public static BasePage getBasePage(){
        return new BasePage();
    }
    public void openUrl(WebDriver driver, String pageUrl) {
        driver.get(pageUrl);
    }

    public void clickToElement(WebDriver driver, String locator) {
        getElement(driver,locator).click();
    }

    public void selectItemDropdown(WebDriver driver, String locator, String textItem) {
        new Select(getElement(driver, locator)).selectByVisibleText(textItem);
    }
    public void sendkeyToElement(WebDriver driver, String locator, String textSendkey) {
        getElement(driver, locator).clear();
        getElement(driver,locator).sendKeys(textSendkey);
    }
    public List<WebElement> getListElement(WebDriver driver, String locator) {
        return driver.findElements(getByXpath(locator));
    }
    public String getSelectedItemInDropdown(WebDriver driver, String locator) {
        return new Select(getElement(driver,locator)).getFirstSelectedOption().getText();
    }
    public boolean isDropdownMultiple (WebDriver driver, String locator) {
        return new Select(getElement(driver,locator)).isMultiple();
    }

    private void selectCustomDropdown(WebDriver driver, String parentXpath, String childXpath, String textItem) throws InterruptedException {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT));
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(parentXpath))).click();
        sleepInSecond(2);

        List<WebElement> allitem = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
        for (WebElement item: allitem) {
            if (item.getText().equals(textItem)) {
                item.click();
                break;
            }
        }
    }

   public String getElementAttribue(WebDriver driver, String locator, String attributeName) {
     String attribute = getElement(driver, locator).getDomAttribute(attributeName);
     return attribute;
    }
    public String getCssValue(WebDriver driver, String locator , String propertyName) {
        return getElement(driver,locator).getCssValue(propertyName);
    }
    public String getHexaColorFromRGBA(String rgbaValue) {
        return Color.fromString(rgbaValue).asHex().toUpperCase();
    }
    public int getElementsSize(WebDriver driver, String locator) {
        return getListElement(driver, locator).size();
    }
    public void checkTheCheckboxOrRadio(WebDriver driver, String locator) {
        if (!getElement(driver, locator).isSelected()) {
            getElement(driver, locator).click();
        }
    }
    public void uncheckTheCheckboxOrRadio(WebDriver driver, String locator) {
        if (getElement(driver, locator).isSelected()) {
            getElement(driver, locator).click();
        }
    }
    public boolean isElementDisplayed(WebDriver driver, String locator) {
        return getElement(driver, locator).isDisplayed();
    }
    public boolean isElementSelected(WebDriver driver, String locator) {
        return getElement(driver, locator).isSelected();
    }
    public boolean isElementEnabled(WebDriver driver, String locator) {
        return getElement(driver, locator).isEnabled();
    }
    public WebDriver switchToFrame(WebDriver driver, String locator) {
        return driver.switchTo().frame(getElement(driver,locator));
    }
    public WebDriver switchToDefaultContent(WebDriver driver) {
        return driver.switchTo().defaultContent();
    }
    public void hoverToElement(WebDriver driver, String locator) {
        new Actions(driver).moveToElement(getElement(driver, locator)).perform();
    }
    public void doubleToElement(WebDriver driver, String locator) {
        new Actions(driver).doubleClick(getElement(driver, locator)).perform();
    }
    public void rightClickToElement(WebDriver driver, String locator) {
        new Actions(driver).contextClick(getElement(driver, locator)).perform();
    }
    public void scrollToElement(WebDriver driver, String locator) {
        new Actions(driver).scrollToElement(getElement(driver, locator)).perform();
    }
    public void sendKeyboardToElement(WebDriver driver, String locator, Keys key) {
        new Actions(driver).sendKeys(getElement(driver,locator), key).perform();
    }
    public String getDomain(WebDriver driver) {
        return (String) ((JavascriptExecutor) driver).executeScript("return document.domain;");
    }
    public void clickToElementByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getElement(driver, locator));
        sleepInSecond(2);
    }
    public String getTextElement(WebDriver driver, String locator) {
        return getElement(driver,locator).getText();
    }
    public void waitForElementVisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
    }
    public void waitForListElementVisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(locator)));
    }
    public void waitForElementInvisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
    }
    public void waitForElementClickable(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
    }








    public void sleepInSecond(long timeInSecond)  {
        try {
            Thread.sleep(timeInSecond);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public By getByXpath(String locator) {
        return By.xpath(locator);
    }
    public WebElement getElement(WebDriver driver, String locator) {
        return driver.findElement(getByXpath(locator));
    }

    private long LONG_TIMEOUT = 30;

}
