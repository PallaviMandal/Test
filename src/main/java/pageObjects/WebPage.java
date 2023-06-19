package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utilities.CommonUtils;
import utilities.HandledException;

public class WebPage {
    public WebDriver iDriver;
    public CommonUtils utils;
    public String searchEngine, searchTerm;

    public WebPage(WebDriver driver) {
        iDriver = driver;
        this.utils = new CommonUtils(driver);
    }

    public String getTitleOfCurrentWebPage() throws HandledException, InterruptedException {
        utils.waitTillPageLoads();
        String pageTitle = utils.getPageTitle();
        return pageTitle;
    }

    public String getURLOfCurrentWebPage() throws HandledException, InterruptedException {
        utils.waitTillPageLoads();
        String actualPageURL = utils.getPageURL();
        System.out.println("actualPageURL ::: " + actualPageURL);
        return actualPageURL;
    }

    public boolean validateTitleContainsSearchTerm(String title, String searchTerm) {
        boolean flag = false;
        String[] searchTerm_ar = searchTerm.split(" ");
        for (int j = 0; j < searchTerm_ar.length; j++) {
            if (title.toLowerCase().contains(searchTerm_ar[j].toLowerCase())) flag = true;
        }
        return flag;
    }
}

