package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LaunchBrowsers {

    public static WebDriver driver;

    public static WebDriver getDriver(String browserName) {
        if (driver == null) {
            if (browserName.equalsIgnoreCase("firefox")) {
                // launch browser for firefox
            } else if (browserName.equalsIgnoreCase("chrome")) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                System.setProperty("webdriver.chrome.driver", "/Users/PMandal/eclipse-workspace/FiservTest/Drivers/chromedriver");
                driver = new ChromeDriver(options);
            } else if (browserName.equalsIgnoreCase("IE")) {
                // launch browser for IE
            }
        }
        return driver;
    }
}
