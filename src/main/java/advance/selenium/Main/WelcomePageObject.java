package advance.selenium.Main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WelcomePageObject extends BasePageObject {

    private String url = "https://www.imdb.com/";

    public WelcomePageObject(WebDriver driver) {
	super(driver);
    }

    // enter url in browser
    public void openWelcomePage() {
	// user open the url
	openUrl(url);
    }

    // In this function sarch for a keyword is done.
    public void searchOnPage(String searchTerm) {

	// find search bar on page
	find(By.id("suggestion-search"));
	// enter the search term
	type(searchTerm, By.id("suggestion-search"));
	// click the search icon to start search
	click(By.id("suggestion-search-button"));
    }

}
