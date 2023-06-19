package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonUtils {
    public WebDriver iDriver;
    public WebDriverWait wait;

    public CommonUtils(WebDriver driver) {
        iDriver = driver;
    }

    public void navigateToURL(String URL) throws HandledException {
        try {
            iDriver.navigate().to(URL);
        } catch (Exception e) {
            throw new HandledException("User did not navigated to correct URL", e);
        }
    }

    public String getPageTitle() throws HandledException {
        try {
            System.out.print(String.format("The title of the page is: %s\n\n", iDriver.getTitle()));
            return iDriver.getTitle();
        } catch (Exception e) {
            throw new HandledException("Page didn't open", e);
        }
    }

    public String getPageURL() throws HandledException {
        try {
            System.out.print(String.format("The URL of the page is: %s\n\n", iDriver.getCurrentUrl()));
            return iDriver.getCurrentUrl();
        } catch (Exception e) {
            throw new HandledException("The URL is empty", e);
        }
    }

    public WebElement getElement(By selector) {
        try {
            return iDriver.findElement(selector);
        } catch (Exception e) {
            System.out.println(String.format("Element %s does not exist - proceeding", selector));
        }
        return null;
    }

    public void sendKeys(WebElement element, String value) throws HandledException {
        clearField(element);
        try {
            element.sendKeys(value);
        } catch (Exception e) {
            throw new HandledException("Issue with entering text", e);
        }
    }

    public void pressEnterKey(WebElement element) throws HandledException {
        try {
            element.sendKeys(Keys.ENTER);
        } catch (Exception e) {
            throw new HandledException("Enter Key was not pressed !", e);
        }
    }


    public void clearField(WebElement element) {
        try {
            element.clear();
        } catch (Exception e) {
            System.out.print(String.format("The following element could not be cleared: [%s]", element.getText()));
        }
    }


    public void click(WebElement element) throws HandledException, InterruptedException {
        waitForElementToBeClickable(element);
        try {
            element.click();
        } catch (Exception e) {
            throw new HandledException(String.format("The following element is not clickable: %s", element), e);
        }
    }

    public void waitForElementToBeClickable(WebElement element) throws HandledException {
        try {
            wait = new WebDriverWait(iDriver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            throw new HandledException(String.format("The following element is not clickable: " + element), e);
        }
    }

    public void waitTillPageLoads() {
        JavascriptExecutor j = (JavascriptExecutor) iDriver;
        if (j.executeScript("return document.readyState").toString().equals("complete")) {
        }
        //iterate 50 times after every one second to verify if in ready state
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("Page has not loaded yet ");
            }
            //again check page state
            if (j.executeScript("return document.readyState").toString().equals("complete")) {
                break;
            }
        }
    }

}
