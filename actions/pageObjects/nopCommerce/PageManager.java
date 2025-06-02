package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;
import pageObjects.nopCommerce.sideBar.CustomerInforPageObject;

public class PageManager {

    // Áp dung Factory Pattern để quản lý khởi tạo Page Object
    public static Object getPage(WebDriver driver , String pageName) {
        switch (pageName) {
            case "HomePage":
                return new UserHomePageObject(driver);
            case "LoginPage":
                return new UserLoginPageObject(driver);
            case "CustomerInfor":
                return new CustomerInforPageObject(driver);
            case "RegisterPage":
                return new UserRegisterPageObject(driver);
            default:
                return new IllegalArgumentException("Page Name is not valid");
        }
    }
}
