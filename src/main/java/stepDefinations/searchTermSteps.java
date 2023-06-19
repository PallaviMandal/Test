package stepDefinations;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pageObjects.SearchEngineHomePage;
import pageObjects.WebPage;
import utilities.HandledException;
import utilities.LaunchBrowsers;

public class searchTermSteps {
    public WebDriver driver;
    public SearchEngineHomePage searchEngineHomePage;
    public WebPage webPage;
    public String searchTerm, navigatedExpectedURL;


    @Given("User Launch {string} Browser")
    public void user_launch_browser(String browser) {

        driver = LaunchBrowsers.getDriver(browser);
        driver.manage().window().maximize();

    }

    @Given("User opens {string} search engine")
    public void user_opens_google_search_engine(String searchEngine) throws HandledException {
        if (searchEngineHomePage == null) searchEngineHomePage = new SearchEngineHomePage(driver);
        searchEngineHomePage.openSearchEngine(searchEngine);
    }

    @Given("User searches for {string} search term")
    public void user_searches_for_search_term(String term) throws HandledException {
        searchTerm = term;
        Assert.assertFalse("User did not enter any term to search", term.isEmpty());
        if (searchEngineHomePage == null) searchEngineHomePage = new SearchEngineHomePage(driver);
        searchEngineHomePage.enterSearchTerm(term);
    }

    @Given("User clicks on the {string} search result")
    public void user_clicks_on_the_search_result(String count) throws HandledException, InterruptedException {
        Assert.assertTrue("User did not select any link to open", StringUtils.isAlphanumeric(count));
        navigatedExpectedURL = searchEngineHomePage.openNthLink(count);
    }

    @And("User validates the redirected URL")
    public void user_validate_redirected_url() throws HandledException, InterruptedException {
        if (webPage == null) webPage = new WebPage(driver);
        String actualURL = webPage.getURLOfCurrentWebPage();
        Assert.assertTrue("User not redirected to expected URL", navigatedExpectedURL.equals(actualURL));
    }

    @And("User navigate to webpage having search term {string}")
    public void user_navigate_to_correct_webpage(String term) throws HandledException, InterruptedException {
        if (webPage == null) webPage = new WebPage(driver);
        String pageTitle = webPage.getTitleOfCurrentWebPage();
        Assert.assertTrue("The actual and expected title don’t match", webPage.validateTitleContainsSearchTerm(pageTitle, term));
    }

}
