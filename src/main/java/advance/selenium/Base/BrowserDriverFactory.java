package advance.selenium.Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserDriverFactory {

    private WebDriver driver;
    private String browser;

    public BrowserDriverFactory(String browser) {
	this.browser = browser;
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
