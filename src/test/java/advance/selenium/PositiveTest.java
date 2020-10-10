package advance.selenium;

import java.util.ArrayList;
import java.util.Map;

import org.testng.annotations.Test;

import advance.selenium.Base.BaseTest;
import advance.selenium.Main.IMDB_API_Search;
import advance.selenium.Main.MostPopularMoviesAndShowPage;
import advance.selenium.Main.SearchResultPage;
import advance.selenium.Main.WelcomePageObject;

public class PositiveTest extends BaseTest {

    @Test
    public void IMDBTest() {

	// search using api
	String searchTerm = "Lords of ring";
	IMDB_API_Search imdb = new IMDB_API_Search();
	Map<Double, String> responseFromAPI = imdb.searchOnIMDB(searchTerm);

	// ---------------FIRST PAGE-------------------------
	// open imdb landing page i.e. welcome page and search for keyword
	WelcomePageObject welcome = new WelcomePageObject(driver);
	welcome.openWelcomePage();
	welcome.searchOnPage(searchTerm);

	// ---------SECOND PAGE ------------------
	// search result page opens, then clicking on the first matched keyword link on
	// this page
	SearchResultPage search = new SearchResultPage(driver);
	search.clickOnKeyWordTitle();

	// ----------- THIRD PAGE------------------
	// Then 'Most popular movie and show' page opens , here sorting is done , then
	// comparing the results with api's response
	MostPopularMoviesAndShowPage mostPopularMoviesAndShowPage = new MostPopularMoviesAndShowPage(driver);
	mostPopularMoviesAndShowPage.sortUsingIMDBRating();
	ArrayList<String> sortedTitleFromWeb = mostPopularMoviesAndShowPage.getTopFiveMatchTitles();
	mostPopularMoviesAndShowPage.verifySearchResultsAccordingToAPIResponse(responseFromAPI, sortedTitleFromWeb);
    }
}
