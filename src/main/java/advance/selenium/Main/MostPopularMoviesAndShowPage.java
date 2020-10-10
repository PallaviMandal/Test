package advance.selenium.Main;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MostPopularMoviesAndShowPage extends BasePageObject {
    public MostPopularMoviesAndShowPage(WebDriver driver) {
	super(driver);
    }

    public void sortUsingIMDBRating() {
	// sorting the titles according to imdb rating using dropdown
	waitForVisiblityOf(By.name("sort"), 8);
	selectFromDropdownByValue(By.name("sort"), "user_rating:descending");
    }

    public ArrayList<String> getTopFiveMatchTitles() {
	// fetching the list of webelement of title visible on webpage(already sorted
	// according to imdb rating)
	waitForVisiblityOf(By.xpath("//*[@class='lister-item-header']/a"), 8);
	List<WebElement> WebelementOnWebPage = findElements(By.xpath("//*[@class='lister-item-header']/a"));

	// fetching the text from the webelements that the titles and storing them in
	// 'titleOnWebPage'.
	ArrayList<String> titleOnWebPage = new ArrayList<>();
	assertTrue(WebelementOnWebPage.size() >= 3,
		"Count of title coming on webpage is less then 3, user need at least 3 search result to compare !");

	for (int i = 0; i < 3; i++) {
	    titleOnWebPage.add(WebelementOnWebPage.get(i).getText());
	}

	return titleOnWebPage;
    }

    public void verifySearchResultsAccordingToAPIResponse(Map<Double, String> responseFromAPI,
	    ArrayList<String> sortedTitleFromWeb) {
	// validating that if the response(titles sorted according to imdb ratings)
	// comes from API is same as the sorted list of titles on webpage

	// fetching the values from Map to compare because values has titles and keys
	// have imdb rating
	ArrayList<String> responseValueFromAPI = new ArrayList(responseFromAPI.values());

	// comparing top 3 results
	for (int i = 0; i < 3; i++) {
	    assertTrue(responseValueFromAPI.get(i).equalsIgnoreCase(sortedTitleFromWeb.get(i)),
		    "Product Title are not matching. Title coming from API is: " + responseValueFromAPI.get(i)
			    + " and Title comung from Web is : " + sortedTitleFromWeb.get(i));
	}
    }
}
