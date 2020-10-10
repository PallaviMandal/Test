package advance.selenium.Main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultPage extends BasePageObject {

    public SearchResultPage(WebDriver driver) {
	super(driver);
    }

    // navigating to a page having list of titles matches with entered keyword
    public void clickOnKeyWordTitle() {
	waitForVisiblityOf(By.xpath("//a[contains(@href,'fn_al_kw_1')]"), 8);
	// finding exact match for keyword on page
	find(By.xpath("//a[contains(@href,'fn_al_kw_1')]"));
	// click on the match in 'Keyword' section of page
	click(By.xpath("//a[contains(@href,'fn_al_kw_1')]"));

    }

}
