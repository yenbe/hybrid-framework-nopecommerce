package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

public class PageManager {

    // Áp dung Factory Pattern để quản lý khởi tạo Page Object
    public static Object getPage(WebDriver driver , String pageName) {
        switch (pageName) {
            case "HomePage":
                return new HomePageObject(driver);
            case "LoginPage":
                return new LoginPageObject(driver);
            case "CustomerInfor":
                return new CustomerInforPageObject(driver);
            case "RegisterPage":
                return new RegisterPageObject(driver);
            default:
                return new IllegalArgumentException("Page Name is not valid");
        }
    }
}
