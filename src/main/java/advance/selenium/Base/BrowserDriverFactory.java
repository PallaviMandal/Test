package advance.selenium.Base;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserDriverFactory {

    private WebDriver driver;
    private String browser;
    private Logger log;

    public BrowserDriverFactory(String browser) {

	this.browser = "chrome";

    }

    public WebDriver createDriver() {
	switch (browser) {
	case "chrome":
	    System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
	    driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    break;
	}
	return driver;
    }
}
