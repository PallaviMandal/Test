package advance.selenium.Base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    protected WebDriver driver;

    @Parameters({ "browser" })
    @BeforeMethod
    public void SetUp(@Optional("chrome") String browser) {
	BrowserDriverFactory factory = new BrowserDriverFactory(browser);
	driver = factory.createDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void TearDown() {
	driver.quit();
    }
}
