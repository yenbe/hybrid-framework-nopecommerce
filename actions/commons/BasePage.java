package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.nopCommerce.*;
import pageObjects.nopCommerce.sideBar.CustomerInforPageObject;
import pageUIs.nopCommerce.BasePageUI;

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
    private Alert waitToAlertPresence(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.alertIsPresent());
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
        return driver.findElements(getByLocator(locator));
    }
    public String getSelectedItemInDropdown(WebDriver driver, String locator) {
        return new Select(getElement(driver,locator)).getFirstSelectedOption().getText();
    }
    public boolean isDropdownMultiple (WebDriver driver, String locator) {
        return new Select(getElement(driver,locator)).isMultiple();
    }

    private void selectCustomDropdown(WebDriver driver, String parentXpath, String childXpath, String textItem) throws InterruptedException {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
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
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }
    public void waitForListElementVisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locator)));
    }
    public void waitForElementInvisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
    }
    public void waitForElementClickable(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
    }

    public void sleepInSecond(long timeInSecond)  {
        try {
            Thread.sleep(timeInSecond);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public CustomerInforPageObject clickMyAccountLinkAtUserSite(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.USER_MYACCOUNT_LINK);
        clickToElement(driver, BasePageUI.USER_MYACCOUNT_LINK);
        return PageGenerator.getPageInstance(CustomerInforPageObject.class,driver);
    }
    public UserHomePageObject clickLogoutLinkAtUserSite(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.USER_LOGOUT_LINK);
        clickToElement(driver, BasePageUI.USER_LOGOUT_LINK);
        return PageGenerator.getPageInstance(UserHomePageObject.class,driver);
    }
    public AdminLoginPageObject clickLogoutLinkAtAdminSite(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.ADMIN_LOGOUT_LINK);
        clickToElement(driver, BasePageUI.ADMIN_LOGOUT_LINK);
        return PageGenerator.getPageInstance(AdminLoginPageObject.class,driver);
    }
    public By getByXpath(String locator) {
        return By.xpath(locator);
    }

    public By getByLocator(String locator) {
        if (locator.isEmpty() || locator == null) {
            throw new RuntimeException("Locator type cannot be null or empty");
        }

        switch (locator.split("=")[0].toLowerCase()) {
            case "xpath":
                return By.xpath(locator.substring(6));
            case "css":
                return By.cssSelector(locator.substring(4));
            case "id":
                return By.id(locator.substring(3));
            case "name":
                return By.name(locator.substring(5));
            case "class":
                return By.className(locator.substring(6));
            default:
                throw new InvalidArgumentException("Locator type is not support.");
        }
    }

    public WebElement getElement(WebDriver driver, String locator) {
        return driver.findElement(getByLocator(locator));
    }

    public AdminLoginPageObject openAdminSite(WebDriver driver, String adminUrl) {
        openUrl(driver,adminUrl);
        return PageGenerator.getPageInstance(AdminLoginPageObject.class, driver);
    }

    public AdminProductPageObject openAdminProductPage(WebDriver driver) {
        String attributeValue = getElementAttribue(driver,BasePageUI.ADMIN_PRODUCT_MENU,"class");
        if (!attributeValue.endsWith("menu-is-opening menu-open")) {
            waitForElementClickable(driver, BasePageUI.ADMIN_PRODUCT_MENU);
            clickToElement(driver, BasePageUI.ADMIN_PRODUCT_MENU);
        }
        waitForElementClickable(driver, BasePageUI.ADMIN_PRODUCT_SUBMENU);
        clickToElement(driver, BasePageUI.ADMIN_PRODUCT_SUBMENU);

        return PageGenerator.getPageInstance(AdminProductPageObject.class,driver);
    }

    public boolean isPageLoadedSuccess(WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }


    public UserHomePageObject openUserSite(WebDriver driver, String userUrl) {
        openUrl(driver, userUrl);
        return PageGenerator.getPageInstance(UserHomePageObject.class,driver);
    }
}
