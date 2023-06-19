package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.CommonUtils;
import utilities.HandledException;

import java.util.List;

public class SearchEngineHomePage {
    public WebDriver iDriver;
    public CommonUtils utils;
    public String searchEngine, searchTerm;
    @FindBy(xpath = "//textarea[@id='APjFqb']")
    WebElement searchBar_google;
    @FindBy(xpath = "xpath for yahoo")
    WebElement searchBar_yahoo;
    @FindBy(xpath = "xpath for bing")
    WebElement searchBar_bing;
    @FindBy(xpath = "//textarea[@id='APjFqb']")
    List<WebElement> searchResults;

    public SearchEngineHomePage(WebDriver driver) {
        iDriver = driver;
        this.utils = new CommonUtils(driver);
        PageFactory.initElements(iDriver, this);
    }

    public void openSearchEngine(String searchEngineName) throws HandledException {
        this.searchEngine = searchEngineName.toLowerCase();
        switch (searchEngineName.toLowerCase()) {
            case "google":
                utils.navigateToURL("https://www.google.com/");
                break;
            case "yahoo":
                utils.navigateToURL("https://www.yahoo.com/");
                break;
            case "bing":
                utils.navigateToURL("https://www.bing.com/");
                break;
            default:
                utils.navigateToURL("https://www.google.com/");
                break;
        }
    }

    public void enterSearchTerm(String term) throws HandledException {
        this.searchTerm = term;
        utils.waitTillPageLoads();
        WebElement ele;
        switch (searchEngine) {
            case "chrome":
                ele = searchBar_google;
                break;
            case "yahoo":
                ele = searchBar_yahoo;
                break;
            case "bing":
                ele = searchBar_bing;
                break;
            default:
                ele = searchBar_google;
                break;
        }
        utils.sendKeys(ele, term);
        utils.pressEnterKey(searchBar_google);
    }

    public String openNthLink(String count) throws HandledException, InterruptedException {
        utils.waitTillPageLoads();
        count = count.replaceAll("[^0-9]", "");
        int n = Integer.parseInt(count);
        List<WebElement> linksOnPage = iDriver.findElements(By.xpath("//div[@id='search']//h3/parent::a[@href]"));
        Assert.assertTrue(n < linksOnPage.size());
        WebElement elementToClick = utils.getElement(By.xpath("(//div[@id='search']//h3/parent::a[@href])[" + n + "]"));
        String navigatedLink = elementToClick.getAttribute("href");
        utils.click(elementToClick);
        return navigatedLink;
    }
}
