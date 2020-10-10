package advance.selenium.Main;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageObject {

    protected WebDriver driver;
    private WebDriverWait wait;

    public BasePageObject(WebDriver driver) {
	this.driver = driver;

    }

    protected void openUrl(String url) {
	driver.get(url);
    }

    protected WebElement find(By loactor) {
	return driver.findElement(loactor);
    }

    protected List<WebElement> findElements(By loactor) {
	return driver.findElements(loactor);
    }

    protected void click(By locator) {
	waitForVisiblityOf(locator, 5);
	driver.findElement(locator).click();
    }

    protected void type(String text, By locator) {
	waitForVisiblityOf(locator, 5);
	find(locator).sendKeys(text);
    }

    protected void waitForVisiblityOf(By locator, Integer sec) {
	try {
	    wait = new WebDriverWait(driver, sec);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	} catch (StaleElementReferenceException e) {
	    e.printStackTrace(System.out);
	    throw new RuntimeException();
	}

    }

    protected void selectFromDropdownByValue(By locator, String value) {
	WebElement sortDropdown = driver.findElement(locator);
	Select dropdown = new Select(sortDropdown);
	dropdown.selectByValue(value);
    }

}
