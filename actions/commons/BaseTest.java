package commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.Random;

public class BaseTest {

    private WebDriver driver;
    protected WebDriver getBrowserDriver(String url, String browserName) {
        switch (browserName) {
            case "edge":
                driver = new EdgeDriver();
                break;
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException("Browser name is not valid");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get(url);
        return driver;
    }
    protected void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
    protected int generateFakeNumber() {
        Random rand = new Random();
        return rand.nextInt(9999);
    }
}
